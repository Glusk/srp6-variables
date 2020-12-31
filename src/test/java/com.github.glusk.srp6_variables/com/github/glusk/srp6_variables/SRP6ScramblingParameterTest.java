package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

import com.github.glusk.srp6_variables.rfc5054.RFC5054ClientPublicKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ScramblingParameter;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ServerPublicKey;


public final class SRP6ScramblingParameterTest {
    @Test
    public void testAgainstRFC5054TestVectors()
        throws NoSuchAlgorithmException {
        assertTrue(
            new RFC5054ScramblingParameter().equals(
                new SRP6ScramblingParameter(
                    new ImmutableMessageDigest(
                        MessageDigest.getInstance("SHA-1")
                    ),
                    new RFC5054ClientPublicKey(),
                    new RFC5054ServerPublicKey(),
                    ByteOrder.BIG_ENDIAN
                )
            ),
            "Computed variable does not match the RFC5054 Test Vector"
        );
    }
}