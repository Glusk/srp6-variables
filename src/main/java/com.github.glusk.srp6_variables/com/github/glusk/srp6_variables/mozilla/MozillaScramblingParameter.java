package com.github.glusk.srp6_variables.mozilla;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Mozilla Test Vector: random scrambling parameter (u). */
public final class MozillaScramblingParameter
    extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "b284aa1064e87751 50da6b5e2147b47c"
              + "a7df505bed94a6f4 bb2ad873332ad732"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
