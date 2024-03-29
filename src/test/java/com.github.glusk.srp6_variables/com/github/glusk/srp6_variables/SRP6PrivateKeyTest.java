package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.PlainText;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

import com.github.glusk.srp6_variables.rfc5054.RFC5054Identity;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Password;
import com.github.glusk.srp6_variables.rfc5054.RFC5054PrivateKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Salt;

import com.github.glusk.srp6_variables.mozilla.MozillaIdentity;
import com.github.glusk.srp6_variables.mozilla.MozillaPassword;
import com.github.glusk.srp6_variables.mozilla.MozillaPrivateKey;
import com.github.glusk.srp6_variables.mozilla.MozillaSalt;

import com.github.glusk.srp6_variables.wiki.WikiIdentity;
import com.github.glusk.srp6_variables.wiki.WikiPassword;
import com.github.glusk.srp6_variables.wiki.WikiPrivateKey;
import com.github.glusk.srp6_variables.wiki.WikiSalt;

import com.github.glusk.srp6_variables.wow.WoWIdentity;
import com.github.glusk.srp6_variables.wow.WoWPassword;
import com.github.glusk.srp6_variables.wow.WoWPrivateKey;
import com.github.glusk.srp6_variables.wow.WoWSalt;

public final class SRP6PrivateKeyTest {
    @Test
    public void testAgainstRFC5054TestVectors()
        throws NoSuchAlgorithmException {
        assertEquals(
            new RFC5054PrivateKey(),
            new SRP6PrivateKey(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-1")
                ),
                new RFC5054Salt(),
                new RFC5054Identity(),
                new RFC5054Password(),
                ByteOrder.BIG_ENDIAN
            ),
            "Computed variable does not match the RFC5054 Test Vector"
        );
    }
    @Test
    public void testAgainstMozillaTestVectors()
        throws NoSuchAlgorithmException {
        assertEquals(
            new MozillaPrivateKey(),
            new SRP6PrivateKey(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-256")
                ),
                new MozillaSalt(),
                new MozillaIdentity(),
                new MozillaPassword(),
                ByteOrder.BIG_ENDIAN
            ),
            "Computed variable does not match the Mozilla Test Vector"
        );
    }
    @Test
    public void testAgainstWikipediaExampleVariables()
        throws NoSuchAlgorithmException {
        assertEquals(
            new WikiPrivateKey(),
            new SRP6CustomIntegerVariable(
                new Hash(
                    new ImmutableMessageDigest(
                        MessageDigest.getInstance("SHA-256")
                    ),
                    new PlainText(
                        new WikiSalt()
                            .asNonNegativeBigInteger()
                            .toString()
                    ),
                    new PlainText(":"),
                    new WikiIdentity(),
                    new PlainText(":"),
                    new WikiPassword()
                ),
                ByteOrder.BIG_ENDIAN
            ),
            "Computed variable does not match the Wikipedia example variable"
        );
    }
    @Test
    public void testAgainstWoWTestVectors()
        throws NoSuchAlgorithmException {
        assertEquals(
            new WoWPrivateKey(),
            new SRP6PrivateKey(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-1")
                ),
                new WoWSalt(),
                new WoWIdentity(),
                new WoWPassword(),
                ByteOrder.LITTLE_ENDIAN
            ),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
