package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.ByteOrder;

import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Multiplier;

import org.junit.jupiter.api.Test;

public class ZeroPaddedTest {
    @Test
    public void throwsWhenTryingToPadALargerNumberToASmallerOne() {
        assertThrows(
            IllegalStateException.class,
            () ->
                new ZeroPadded(
                    new RFC5054Prime(),
                    ByteOrder.BIG_ENDIAN,
                    new RFC5054Multiplier()
                ).asArray()
        );
    }
}
