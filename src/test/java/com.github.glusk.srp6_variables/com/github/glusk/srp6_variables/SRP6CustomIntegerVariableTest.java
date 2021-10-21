package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.nio.ByteOrder;

import org.junit.jupiter.api.Test;

public final class SRP6CustomIntegerVariableTest {
    @Test
    public void zeroPadsBigEndianToSize() throws SRP6PaddingException {
        assertArrayEquals(
            new byte[] {0, 0, 1, 2, 0},
            new SRP6CustomIntegerVariable(
                new byte[] {1, 2, 0},
                ByteOrder.BIG_ENDIAN
            ).bytes(ByteOrder.BIG_ENDIAN, 5).asArray()
        );
    }
    @Test
    public void zeroPadsLittleEndianToSize() throws SRP6PaddingException {
        assertArrayEquals(
            new byte[] {0, 2, 1, 0, 0},
            new SRP6CustomIntegerVariable(
                new byte[] {0, 2, 1},
                ByteOrder.LITTLE_ENDIAN
            ).bytes(ByteOrder.LITTLE_ENDIAN, 5).asArray()
        );
    }
    @Test
    public void throwsIfPaddingLengthIsTooShort() {
        assertThrows(
            SRP6PaddingException.class,
            () ->
                new SRP6CustomIntegerVariable(
                    new byte[] {1, 2, 0},
                    ByteOrder.BIG_ENDIAN
                ).bytes(ByteOrder.BIG_ENDIAN, 2).asArray()
        );
    }
    @Test
    public void sameMinimalRepresentationsEqual() {
        assertEquals(
            new SRP6CustomIntegerVariable(
                new byte[] {1, 2, 0},
                ByteOrder.BIG_ENDIAN
            ),
            new SRP6CustomIntegerVariable(
                new byte[] {1, 2, 0},
                ByteOrder.BIG_ENDIAN
            )
        );
    }
    @Test
    public void sameObjectsEqual() {
        SRP6IntegerVariable s0 =
            new SRP6CustomIntegerVariable(
                new byte[] {1, 2, 0},
                ByteOrder.BIG_ENDIAN
            );
        assertTrue(s0.equals(s0));
    }
    @Test
    public void comparisonToObjectOfAnotherTypeYieldsFalse() {
        SRP6IntegerVariable s0 =
            new SRP6CustomIntegerVariable(
                new byte[] {1},
                ByteOrder.BIG_ENDIAN
            );
        assertFalse(s0.equals("1"));
    }
    @Test
    public void objectsThatEqualHaveSameHashCode() {
        SRP6IntegerVariable s0 =
            new SRP6CustomIntegerVariable(
                new byte[] {1, 2, 0},
                ByteOrder.BIG_ENDIAN
            );
        SRP6IntegerVariable s1 =
            new SRP6CustomIntegerVariable(
                new byte[] {1, 2, 0},
                ByteOrder.BIG_ENDIAN
            );
        assertTrue(s0.equals(s1) && s0.hashCode() == s1.hashCode());
    }
    @Test
    public void trimsLeadingZeroes() {
        assertArrayEquals(
            new byte[] {1, 2, 0},
            new SRP6CustomIntegerVariable(
                new byte[] {0, 1, 2, 0},
                ByteOrder.BIG_ENDIAN
            ).bytes(ByteOrder.BIG_ENDIAN).asArray()
        );
    }
    @Test
    public void returnsLittleEndianByteSequence() {
        assertArrayEquals(
            new byte[] {0, 2, 1},
            new SRP6CustomIntegerVariable(
                new byte[] {1, 2, 0},
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
