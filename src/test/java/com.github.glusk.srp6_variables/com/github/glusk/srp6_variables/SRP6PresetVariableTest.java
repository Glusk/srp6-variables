package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.math.BigInteger;
import java.nio.ByteOrder;

import org.junit.jupiter.api.Test;

public final class SRP6PresetVariableTest {
    @Test
    public void trimsLeadingZeroes() {
        assertArrayEquals(
            new byte[] {1, 2, 0},
            new SRP6PresetVariable(
                () -> new byte[] {0, 1, 2, 0},
                ByteOrder.BIG_ENDIAN
            ).bytes(ByteOrder.BIG_ENDIAN).asArray()
        );
    }
    @Test
    public void zeroReturnsAnEmptyByteSequence() {
        assertArrayEquals(
            new byte[0],
            new SRP6PresetVariable(BigInteger.ZERO)
                .bytes(ByteOrder.BIG_ENDIAN)
                .asArray()
        );
    }
}
