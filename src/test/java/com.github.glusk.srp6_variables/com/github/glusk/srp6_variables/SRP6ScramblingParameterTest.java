package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.PlainText;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

import com.github.glusk.srp6_variables.rfc5054.RFC5054ClientPublicKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ScramblingParameter;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ServerPublicKey;

import com.github.glusk.srp6_variables.wiki.WikiClientPublicKey;
import com.github.glusk.srp6_variables.wiki.WikiScramblingParameter;
import com.github.glusk.srp6_variables.wiki.WikiServerPublicKey;

import com.github.glusk.srp6_variables.wow.WoWClientPublicKey;
import com.github.glusk.srp6_variables.wow.WoWPrime;
import com.github.glusk.srp6_variables.wow.WoWScramblingParameter;
import com.github.glusk.srp6_variables.wow.WoWServerPublicKey;

public final class SRP6ScramblingParameterTest {
    @Test
    public void testAgainstRFC5054TestVectors()
        throws NoSuchAlgorithmException, SRP6PaddingException {
        assertTrue(
            new RFC5054ScramblingParameter().equals(
                new SRP6ScramblingParameter(
                    new ImmutableMessageDigest(
                        MessageDigest.getInstance("SHA-1")
                    ),
                    new RFC5054ClientPublicKey(),
                    new RFC5054ServerPublicKey(),
                    new RFC5054Prime(),
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
            new WikiScramblingParameter().equals(
                new SRP6CustomIntegerVariable(
                    new Hash(
                        new ImmutableMessageDigest(
                            MessageDigest.getInstance("SHA-256")
                        ),
                        new PlainText(
                            new WikiClientPublicKey()
                                .asNonNegativeBigInteger()
                          + ":"
                          + new WikiServerPublicKey()
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
        assertTrue(
            new WoWScramblingParameter().equals(
                new SRP6ScramblingParameter(
                    new ImmutableMessageDigest(
                        MessageDigest.getInstance("SHA-1")
                    ),
                    new WoWClientPublicKey(),
                    new WoWServerPublicKey(),
                    new WoWPrime(),
                    ByteOrder.LITTLE_ENDIAN
                )
            ),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
