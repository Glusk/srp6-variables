package com.github.glusk.srp6_variables.wiki;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Wikipedia Python example variable: multiplier parameter (k).*/
public final class WikiMultiplier extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "b317ec55 3cb1a522 01d79b7c 12d4b665"
              + "d0dc234f dbfd5a06 894c1a19 4f818c4a"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
