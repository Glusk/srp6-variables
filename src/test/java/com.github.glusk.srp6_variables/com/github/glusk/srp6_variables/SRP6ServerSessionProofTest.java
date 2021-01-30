package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.PlainText;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

import com.github.glusk.srp6_variables.mozilla.MozillaServerSessionProof;
import com.github.glusk.srp6_variables.mozilla.MozillaClientSessionProof;
import com.github.glusk.srp6_variables.mozilla.MozillaPrime;
import com.github.glusk.srp6_variables.mozilla.MozillaSharedSecret;
import com.github.glusk.srp6_variables.mozilla.MozillaClientPublicKey;

import com.github.glusk.srp6_variables.wiki.WikiServerSessionProof;
import com.github.glusk.srp6_variables.wiki.WikiClientPublicKey;
import com.github.glusk.srp6_variables.wiki.WikiClientSessionProof;
import com.github.glusk.srp6_variables.wiki.WikiSessionKey;

import com.github.glusk.srp6_variables.wow.WoWServerSessionProof;
import com.github.glusk.srp6_variables.wow.WoWClientSessionProof;
import com.github.glusk.srp6_variables.wow.WoWPrime;
import com.github.glusk.srp6_variables.wow.WoWSessionKey;
import com.github.glusk.srp6_variables.wow.WoWClientPublicKey;

public final class SRP6ServerSessionProofTest {
    @Test
    public void testAgainstMozillaTestVectors()
        throws NoSuchAlgorithmException, SRP6PaddingException {
        assertArrayEquals(
            new MozillaServerSessionProof().asArray(),
            new SRP6ServerSessionProof(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-256")
                ),
                new MozillaPrime(),
                new MozillaClientPublicKey(),
                new MozillaClientSessionProof(),
                new MozillaSharedSecret(),
                ByteOrder.BIG_ENDIAN
            ).asArray(),
            "Computed variable does not match the Mozilla Test Vector"
        );
    }
    @Test
    public void testAgainstWikipediaExampleVariables()
        throws NoSuchAlgorithmException {
        assertTrue(
            new WikiServerSessionProof().equals(
                new SRP6CustomIntegerVariable(
                    new Hash(
                        new ImmutableMessageDigest(
                            MessageDigest.getInstance("SHA-256")
                        ),
                        new PlainText(
                            new WikiClientPublicKey()
                                .asNonNegativeBigInteger()
                          + ":"
                          + new WikiClientSessionProof()
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
        throws NoSuchAlgorithmException, SRP6PaddingException {
        assertArrayEquals(
            new WoWServerSessionProof().asArray(),
            new SRP6ServerSessionProof(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-1")
                ),
                new WoWPrime(),
                new WoWClientPublicKey(),
                new WoWClientSessionProof(),
                new WoWSessionKey(),
                ByteOrder.LITTLE_ENDIAN
            ).asArray(),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
