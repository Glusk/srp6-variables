package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.PlainText;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

import com.github.glusk.srp6_variables.mozilla.MozillaPrime;
import com.github.glusk.srp6_variables.mozilla.MozillaClientPublicKey;
import com.github.glusk.srp6_variables.mozilla.MozillaServerPublicKey;
import com.github.glusk.srp6_variables.mozilla.MozillaSharedSecret;
import com.github.glusk.srp6_variables.mozilla.MozillaClientSessionProof;

import com.github.glusk.srp6_variables.wiki.WikiPrime;
import com.github.glusk.srp6_variables.wiki.WikiGenerator;
import com.github.glusk.srp6_variables.wiki.WikiIdentity;
import com.github.glusk.srp6_variables.wiki.WikiSalt;
import com.github.glusk.srp6_variables.wiki.WikiClientPublicKey;
import com.github.glusk.srp6_variables.wiki.WikiServerPublicKey;
import com.github.glusk.srp6_variables.wiki.WikiClientSessionProof;
import com.github.glusk.srp6_variables.wiki.WikiSessionKey;

import com.github.glusk.srp6_variables.wow.WoWClientSessionProof;
import com.github.glusk.srp6_variables.wow.WoWPrime;
import com.github.glusk.srp6_variables.wow.WoWGenerator;
import com.github.glusk.srp6_variables.wow.WoWIdentity;
import com.github.glusk.srp6_variables.wow.WoWSalt;
import com.github.glusk.srp6_variables.wow.WoWSessionKey;
import com.github.glusk.srp6_variables.wow.WoWClientPublicKey;
import com.github.glusk.srp6_variables.wow.WoWServerPublicKey;

public final class SRP6ClientSessionProofTest {
    @Test
    public void testAgainstMozillaTestVectors()
        throws NoSuchAlgorithmException {
        assertEquals(
            new MozillaClientSessionProof(),
            new SRP6ClientSessionProof(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-256")
                ),
                new MozillaPrime(),
                new MozillaClientPublicKey(),
                new MozillaServerPublicKey(),
                new MozillaSharedSecret(),
                ByteOrder.BIG_ENDIAN
            ),
            "Computed variable does not match the Mozilla Test Vector"
        );
    }
    @Test
    public void testAgainstWikipediaExampleVariables()
        throws NoSuchAlgorithmException {
        ImmutableMessageDigest imd =
            new ImmutableMessageDigest(
                MessageDigest.getInstance("SHA-256")
            );

        assertTrue(
            new WikiClientSessionProof().equals(
                new SRP6CustomIntegerVariable(
                    new Hash(
                        imd,
                        new PlainText(
                            new BigInteger(
                                new Hash(
                                    imd,
                                    new PlainText(
                                        new WikiPrime()
                                            .asNonNegativeBigInteger()
                                            .toString()
                                    )
                                ).asArray()
                            ).xor(
                                new BigInteger(
                                    new Hash(
                                        imd,
                                        new PlainText(
                                            new WikiGenerator()
                                                .asNonNegativeBigInteger()
                                                .toString()
                                        )
                                    ).asArray()
                                )
                            )
                          + ":"
                          + new BigInteger(
                                new Hash(imd, new WikiIdentity()).asArray()
                            )
                          + ":"
                          + new WikiSalt()
                                .asNonNegativeBigInteger()
                          + ":"
                          + new WikiClientPublicKey()
                                .asNonNegativeBigInteger()
                          + ":"
                          + new WikiServerPublicKey()
                                .asNonNegativeBigInteger()
                          + ":"
                          + new WikiSessionKey()
                                .asNonNegativeBigInteger()
                        )
                    ),
                    ByteOrder.BIG_ENDIAN
                )
            ),
            "Computed variable does not match the Wikipedia example variable"
        );
    }
    @Test
    public void testAgainstWoWTestVectors()
        throws NoSuchAlgorithmException {
        assertEquals(
            new WoWClientSessionProof(),
            new SRP6ClientSessionProof(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-1")
                ),
                new WoWPrime(),
                new WoWGenerator(),
                new WoWIdentity(),
                new WoWSalt(),
                new WoWClientPublicKey(),
                new WoWServerPublicKey(),
                new WoWSessionKey(),
                ByteOrder.LITTLE_ENDIAN
            ),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
