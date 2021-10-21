package com.github.glusk.srp6_variables.mozilla;

import java.math.BigInteger;
import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Mozilla Test Vector: multiplier (k). */
public final class MozillaMultiplier extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    @SuppressWarnings("checkstyle:linelength")
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new BigInteger(
                "2590038599070950300691544216303772122846747035652616593381637186118123578112"
            )
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
