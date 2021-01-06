package com.github.glusk.srp6_variables.wow;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** WoW Test Vector: premaster secret (S). */
public final class WoWPremasterSecret implements SRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "4B9AA92F C209E663 EF63E3D2 98661577"
              + "927A741E 7FCAD8E1 D6F70CD6 55FAA645"
            ),
            ByteOrder.LITTLE_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
