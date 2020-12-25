package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;

import org.junit.jupiter.api.Test;

public final class SRP6VariableTest {
    @Test
    public void zeroPadsToBigEndianToSize() {
        assertArrayEquals(
            new byte[] {0, 0, 1, 2, 0},
            new SRP6Variable() {
                @Override
                public Bytes bytes(final ByteOrder order) {
                    // Big-endian representation
                    return () -> new byte[] {1, 2, 0};
                }
            }.bytes(ByteOrder.BIG_ENDIAN, 5).asArray()
        );
    }
    @Test
    public void zeroPadsLittleEndianToSize() {
        assertArrayEquals(
            new byte[] {0, 2, 1, 0, 0},
            new SRP6Variable() {
                @Override
                public Bytes bytes(final ByteOrder order) {
                    // Little-endian representation
                    return () -> new byte[] {0, 2, 1};
                }
            }.bytes(ByteOrder.LITTLE_ENDIAN, 5).asArray()
        );
    }
    @Test
    public void throwsIfPaddingLengthIsTooShort() {
        assertThrows(
            SRP6PaddingException.class,
            () -> {
                new SRP6Variable() {
                    @Override
                    public Bytes bytes(final ByteOrder order) {
                        // Big-endian representation
                        return () -> new byte[] {1, 2, 0};
                    }
                }.bytes(ByteOrder.BIG_ENDIAN, 2).asArray();
            }
        );
    }
}
