package com.github.glusk.srp6_variables.wiki;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6PresetIntegerVariable;

/** Wikipedia Python example variable: private key (x). */
public final class WikiPrivateKey implements SRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6PresetIntegerVariable(
            new Hex(
                "28a914ef 69978f5f e544f030 bea89eab"
              + "675bcaa2 ec79cd36 efa1d410 d27d5215"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
