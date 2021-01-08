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

import com.github.glusk.srp6_variables.wiki.WikiServerSessionProof;
import com.github.glusk.srp6_variables.wiki.WikiClientPublicKey;
import com.github.glusk.srp6_variables.wiki.WikiClientSessionProof;
import com.github.glusk.srp6_variables.wiki.WikiSessionKey;

import com.github.glusk.srp6_variables.wow.WoWServerSessionProof;
import com.github.glusk.srp6_variables.wow.WoWClientSessionProof;
import com.github.glusk.srp6_variables.wow.WoWSessionKey;
import com.github.glusk.srp6_variables.wow.WoWClientPublicKey;

public final class SRP6ServerSessionProofTest {
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
                                .toString()
                        ),
                        new PlainText(":"),
                        new PlainText(
                            new WikiClientSessionProof()
                                .asNonNegativeBigInteger()
                                .toString()
                        ),
                        new PlainText(":"),
                        new PlainText(
                            new WikiSessionKey()
                                .asNonNegativeBigInteger()
                                .toString()
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
        assertArrayEquals(
            new WoWServerSessionProof().asArray(),
            new SRP6ServerSessionProof(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-1")
                ),
                new WoWClientPublicKey(),
                new WoWClientSessionProof(),
                new WoWSessionKey(),
                ByteOrder.LITTLE_ENDIAN
            ).asArray(),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
