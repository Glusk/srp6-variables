package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.github.glusk.caesar.PlainText;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

import com.github.glusk.srp6_variables.mozilla.MozillaSessionKey;
import com.github.glusk.srp6_variables.mozilla.MozillaSharedSecret;
import com.github.glusk.srp6_variables.mozilla.MozillaPrime;

import com.github.glusk.srp6_variables.wiki.WikiSessionKey;
import com.github.glusk.srp6_variables.wiki.WikiPremasterSecret;

import com.github.glusk.srp6_variables.wow.WoWPremasterSecret;
import com.github.glusk.srp6_variables.wow.WoWSessionKey;

public final class SRP6SessionKeyTest {
    @Test
    public void testAgainstMozillaExampleVariables()
        throws NoSuchAlgorithmException, SRP6PaddingException {
        assertEquals(
            new MozillaSessionKey(),
            new Hash(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-256")
                ),
                new MozillaSharedSecret().bytes(
                    ByteOrder.BIG_ENDIAN,
                    new MozillaPrime().bytes(
                        ByteOrder.BIG_ENDIAN
                    ).asArray().length
                )
            ),
            "Computed variable does not match the Mozilla example variable"
        );
    }
    @Test
    public void testAgainstWikipediaExampleVariables()
        throws NoSuchAlgorithmException {
        assertEquals(
            new WikiSessionKey(),
            new SRP6CustomIntegerVariable(
                new Hash(
                    new ImmutableMessageDigest(
                        MessageDigest.getInstance("SHA-256")
                    ),
                    new PlainText(
                        new WikiPremasterSecret()
                            .asNonNegativeBigInteger()
                            .toString()
                    )
                ),
                ByteOrder.BIG_ENDIAN
            ),
            "Computed variable does not match the Wikipedia example variable"
        );
    }
    @Test
    public void testAgainstWoWTestVectors() throws NoSuchAlgorithmException {
        assertEquals(
            new WoWSessionKey(),
            new SRP6SessionKey(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-1")
                ),
                new WoWPremasterSecret(),
                ByteOrder.LITTLE_ENDIAN
            ),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
