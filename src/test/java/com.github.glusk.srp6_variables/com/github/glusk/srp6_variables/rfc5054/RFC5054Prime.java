package com.github.glusk.srp6_variables.rfc5054;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** RFC5054 Test Vector: prime (N). */
public final class RFC5054Prime extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "EEAF0AB9 ADB38DD6 9C33F80A FA8FC5E8 60726187 75FF3C0B"
              + "9EA2314C 9C256576 D674DF74 96EA81D3 383B4813 D692C6E0"
              + "E0D5D8E2 50B98BE4 8E495C1D 6089DAD1 5DC7D7B4 6154D6B6"
              + "CE8EF4AD 69B15D49 82559B29 7BCF1885 C529F566 660E57EC"
              + "68EDBC3C 05726CC0 2FD4CBF4 976EAA9A FD5138FE 8376435B"
              + "9FC61D2F C0EB06E3"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
