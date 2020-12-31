package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.math.BigInteger;
import java.nio.ByteOrder;

import org.junit.jupiter.api.Test;

public final class SRP6CustomIntegerVariableTest {
    @Test
    public void trimsLeadingZeroes() {
        assertArrayEquals(
            new byte[] {1, 2, 0},
            new SRP6CustomIntegerVariable(
                () -> new byte[] {0, 1, 2, 0},
                ByteOrder.BIG_ENDIAN
            ).bytes(ByteOrder.BIG_ENDIAN).asArray()
        );
    }
    @Test
    public void returnsLittleEndianByteSequence() {
        assertArrayEquals(
            new byte[] {0, 2, 1},
            new SRP6CustomIntegerVariable(
                () -> new byte[] {1, 2, 0},
                ByteOrder.BIG_ENDIAN
            ).bytes(ByteOrder.LITTLE_ENDIAN).asArray()
        );
    }
    @Test
    public void zeroReturnsAnEmptyByteSequence() {
        assertArrayEquals(
            new byte[0],
            new SRP6CustomIntegerVariable(BigInteger.ZERO)
                .bytes(ByteOrder.BIG_ENDIAN)
                .asArray()
        );
    }
}
