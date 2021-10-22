package com.github.glusk.srp6_variables.wow;

import java.math.BigInteger;
import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** WoW Test Vector: multiplier (k). */
public final class WoWMultiplier extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            BigInteger.valueOf(3)
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
