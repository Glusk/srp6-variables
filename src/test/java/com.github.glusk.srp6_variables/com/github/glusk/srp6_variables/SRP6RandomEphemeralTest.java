package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;

public final class SRP6RandomEphemeralTest {
    @Test
    public void returnsTheSameValueOnMultipleInvocations() {
        SRP6IntegerVariable r =
            new SRP6RandomEphemeral(
                new SecureRandom(),
                new RFC5054Prime()
            );
        assertEquals(
            r.asNonNegativeBigInteger(),
            r.asNonNegativeBigInteger(),
            "Subsequent invocations don't return the same value."
        );
    }
    @Test
    public void throwsIfBitLengthIsTooLong() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new SRP6RandomEphemeral(
                    new SecureRandom(),
                    new RFC5054Prime()
                        .asNonNegativeBigInteger()
                        .bitLength() + 1,
                    new RFC5054Prime()
                );
            }
        );
    }
    @RepeatedTest(100)
    public void defaultsToMinimumBitLength() {
        assertEquals(
            256,
            new SRP6RandomEphemeral(
                new SecureRandom(),
                new RFC5054Prime()
            )
            .asNonNegativeBigInteger()
            .bitLength()
        );
    }
}
