package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.PlainText;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

import com.github.glusk.srp6_variables.rfc5054.RFC5054Generator;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Multiplier;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;

import com.github.glusk.srp6_variables.wiki.WikiGenerator;
import com.github.glusk.srp6_variables.wiki.WikiMultiplier;
import com.github.glusk.srp6_variables.wiki.WikiPrime;

import com.github.glusk.srp6_variables.wow.WoWMultiplier;

public final class SRP6MultiplierTest {
    @Test
    public void testAgainstRFC5054TestVectors()
        throws NoSuchAlgorithmException, SRP6PaddingException {
        assertTrue(
            new RFC5054Multiplier().equals(
                new SRP6Multiplier(
                    new ImmutableMessageDigest(
                        MessageDigest.getInstance("SHA-1")
                    ),
                    new RFC5054Prime(),
                    new RFC5054Generator(),
                    ByteOrder.BIG_ENDIAN
                )
            ),
            "Computed variable does not match the RFC5054 Test Vector"
        );
    }
    @Test
    public void testAgainstWikipediaExampleVariables()
        throws NoSuchAlgorithmException {
        assertTrue(
            new WikiMultiplier().equals(
                new SRP6CustomIntegerVariable(
                    new Hash(
                        new ImmutableMessageDigest(
                            MessageDigest.getInstance("SHA-256")
                        ),
                        new PlainText(
                            new WikiPrime()
                                .asNonNegativeBigInteger()
                          + ":"
                          + new WikiGenerator()
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
    public void testAgainstWoWTestVectors() {
        assertTrue(
            new WoWMultiplier().equals(
                new SRP6Multiplier()
            ),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
