# SRP-6 Variables

[![Build Status](https://travis-ci.com/Glusk/srp6-variables.svg?branch=master)](https://travis-ci.com/Glusk/srp6-variables)
[![Build status](https://ci.appveyor.com/api/projects/status/4dlyh0qkyd7aubpk/branch/master?svg=true)](https://ci.appveyor.com/project/Glusk/srp6-variables/branch/master)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4b28e7a9389046a98c42f6a6eaa00ad8)](https://www.codacy.com/gh/Glusk/srp6-variables/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Glusk/srp6-variables&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/github/Glusk/srp6-variables/badge.svg?branch=master)](https://coveralls.io/github/Glusk/srp6-variables?branch=master)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.glusk/srp6-variables/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.glusk/srp6-variables)
[![javadoc](https://javadoc.io/badge2/com.github.glusk/srp6-variables/javadoc.svg)](https://javadoc.io/doc/com.github.glusk/srp6-variables)

[![LoC](https://tokei.rs/b1/github/glusk/srp6-variables)](https://github.com/Glusk/srp6-variables)
[![Hits-of-Code](https://hitsofcode.com/github/glusk/srp6-variables?branch=master)](https://hitsofcode.com/view/github/glusk/srp6-variables?branch=master)

A Java library of cryptographic primitives required to implement the SRP-6
protocol, built with [Caesar](https://github.com/Glusk/caesar).

## Motivation

Some of the existing open source SRP-6 Java libraries include:
- [Randgalt/srpforjava](https://github.com/Randgalt/srpforjava)
- [Bouncy Castle Provider
](https://search.maven.org/artifact/org.bouncycastle/bcprov-jdk15to18/1.68/pom)
  - [org.bouncycastle.tls.crypto.impl.jcajce.srp](https://github.com/bcgit/bc-java/tree/master/tls/src/main/java/org/bouncycastle/tls/crypto/impl/jcajce/srp)
- [Nimbus SRP6a](https://search.maven.org/artifact/com.nimbusds/srp6a/2.1.0/pom)
- [SRP6 for C# and Java](https://sourceforge.net/projects/srp6-for-csharp-and-java/)
- [GNU Crypto](https://search.maven.org/artifact/org.gnu/gnu-crypto/2.0.1/pom)
  - [gnu.crypto.key.srp6](http://cvs.savannah.gnu.org/viewvc/gnu-crypto/gnu-crypto/source/gnu/crypto/key/srp6/?hideattic=0)

What I've found is that such libraries are not easy to extend.

Most of them work only with *big-endian* byte array representations and some of them aren't compliant with [RFC 5054](https://tools.ietf.org/html/rfc5054).

With that in mind, I decided to create a library of basic building blocks (_**SRP-6 Variables**_) that one can use to implement the protocol.

## Usage

In order to use this library, you must first settle on a couple of constants:

-   group parameters
-   source of randomness
-   hash function
-   byte order

For the purposes of demonstration, the following constants will be used
throughout this page:
``` java
// Group parameters
SRP6IntegerVariable N =
    new SRP6CustomIntegerVariable(
        new Hex(
            "EEAF0AB9 ADB38DD6 9C33F80A FA8FC5E8 60726187 75FF3C0B"
          + "9EA2314C 9C256576 D674DF74 96EA81D3 383B4813 D692C6E0"
          + "E0D5D8E2 50B98BE4 8E495C1D 6089DAD1 5DC7D7B4 6154D6B6"
          + "CE8EF4AD 69B15D49 82559B29 7BCF1885 C529F566 660E57EC"
          + "68EDBC3C 05726CC0 2FD4CBF4 976EAA9A FD5138FE 8376435B"
          + "9FC61D2F C0EB06E3"
        ),
        ByteOrder.BIG_ENDIAN
    );
SRP6IntegerVariable g =
    new SRP6CustomIntegerVariable(
        BigInteger.valueOf(2)
    );

// Source of randomness
SecureRandom rng = new SecureRandom();

// Hash function
ImmutableMessageDigest imd =
    new ImmutableMessageDigest(
        MessageDigest.getInstance("SHA-256")
    );

// Byte order
ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
```

### Creating records

Say we want to create a new record of the form `<I, s, v>` for user `"alice"`
with password `"password123"`:

``` java
Bytes I = new PlainText("alice");
Bytes P = new PlainText("password123");

Bytes s = Bytes.wrapped(rng.generateSeed(32));

SRP6IntegerVariable x = new SRP6PrivateKey(imd, s, I, P, byteOrder);
SRP6IntegerVariable v = new SRP6Verifier(N, g, x);
```

### Client side authentication

This example is based on the optimized message ordering, as described [here][1]:

1.  First, client sends his or her username (`I`) to the server.

2.  The server then responds with: `N`, `g`, `s` and `B`.

3.  Client then performs the following computations:
    ``` java
    SRP6IntegerVariable x = new SRP6PrivateKey(imd, s, I, P, byteOrder);
    SRP6IntegerVariable a = new SRP6RandomEphemeral(rng, N);
    SRP6IntegerVariable A = new SRP6ClientPublicKey(N, g, a);
    SRP6IntegerVariable u = new SRP6ScramblingParameter(imd, A, B, N, byteOrder);
    SRP6IntegerVariable k = new SRP6Multiplier();
    // for SRP-6a:
    // SRP6IntegerVariable k = new SRP6Multiplier(imd, N, g, byteOrder);
    SRP6IntegerVariable S = new SRP6ClientSharedSecret(N, g, k, B, x, u, a);
    Bytes K = new SessionKey(imd, S, byteOrder);
    Bytes M1 = new ClientSessionProof(imd, N, g, I, s, A, B, K, byteOrder);
    ```
    and responds with `A` and `M1`:
    ``` java
    try {
        byte[] bufferA = A.bytes(byteOrder).asArray();
        byte[] bufferM1 = M1.asArray();
        // send over 'bufferA' and 'bufferM1' to the server
        // ...
    } catch (IllegalStateException e) {
        // Immediately abort SRP-6 login!
        // Under no circumstances show the server A and M1!
    }
    ```

4.  Finally, the server responds with `M2` and client checks its validity:
    ``` java
    Bytes cM2 = new ServerSessionProof(imd, N, A, M1, K, byteOrder);
    try {
        if (cM2.equals(M2)) {
            // Authentication successful!
        } else {
            // Authentication failed: server proof mismatch.
        }
    } catch (IllegalStateException e) {
         // Immediately abort SRP-6 login!
    }
    ```

### Server side authentication

This example is based on the optimized message ordering, as described [here][1]:

1.  First, the server receives client username (`I`).

2.  Then the server performs the following computations:
    ``` java
    // lookup and fetch the record by I -> <I, s, v>
    SRP6IntegerVariable b = new SRP6RandomEphemeral(rng, N);
    SRP6IntegerVariable k = new SRP6Multiplier();
    // for SRP-6a:
    // SRP6IntegerVariable k = new SRP6Multiplier(imd, N, g, byteOrder);
    SRP6IntegerVariable B = new SRP6ServerPublicKey(N, g, k, v, b);
    ```
    and responds with: `N`, `g`, `s`, and `B`:
    ``` java
    try {
        byte[] bufferN = N.bytes(byteOrder).asArray();
        byte[] buffer_g = g.bytes(byteOrder).asArray();
        byte[] buffer_s = s.asArray();
        byte[] bufferB = B.bytes(byteOrder).asArray();
        // send over 'bufferN', 'buffer_g', 'buffer_s', 'bufferB' to the client
        // ...
    } catch (IllegalStateException e) {
        // Immediately abort SRP-6 login!
        // Under no circumstances show the client N, g, s, or B.
    }
    ```

3.  Client then responds with `A` and `M1` and the server performs these
    additional computations and responds with `M2`:
    ``` java
    SRP6IntegerVariable u = new SRP6ScramblingParameter(imd, A, B, N, byteOrder);
    SRP6IntegerVariable S = new ServerSharedSecret(N, A, v, u, b);
    Bytes K = new SessionKey(imd, S, byteOrder);
    Bytes sM1 = new ClientSessionProof(imd, N, g, I, s, A, B, K, byteOrder);
    try { 
        if (sM1.equals(M1)) {
            // Authentication successful!
            Bytes M2 = new ServerSessionProof(imd, N, A, M1, K, byteOrder);
            byte[] buffer_M2 = M2.asArray();
            // send over 'bufferM2' to the client
            // ...
        } else {
            // Authentication failed: client proof mismatch.
        }
    } catch (IllegalStateException e) {
        // Immediately abort SRP-6 login!
        // Under no circumstances show the client M2.
    }
    ```

[1]: http://srp.stanford.edu/srp6.ps (WU, Thomas. *SRP-6: Improvements and Refinements to the Secure Remote Password Protocol*)

---

## Releases

Use the [release](./release.sh) script with the following arguments:

1.  `release` - the next release version

2.  `snapshot` - the next snapshot version

3.  `dryRun` (optional) - if set to `true`, the changes will not be pushed
   to the remote repository

Example:

``` bash
./release.sh 0.1.1 0.1.2-SNAPSHOT
```
