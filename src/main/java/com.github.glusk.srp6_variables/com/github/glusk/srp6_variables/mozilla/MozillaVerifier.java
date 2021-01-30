package com.github.glusk.srp6_variables.mozilla;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Mozilla Test Vector: srpVerifier (v). */
public final class MozillaVerifier implements SRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "00173ffa0263e63c cfd6791b8ee2a40f 048ec94cd95aa8a3"
              + "125726f9805e0c82 83c658dc0b607fbb 25db68e68e93f265"
              + "8483049c68af7e82 14c49fde2712a775 b63e545160d64b00"
              + "189a86708c69657d a7a1678eda0cd79f 86b8560ebdb1ffc2"
              + "21db360eab901d64 3a75bf1205070a57 91230ae56466b8c3"
              + "c1eb656e19b794f1 ea0d2a077b3a7553 50208ea0118fec8c"
              + "4b2ec344a05c66ae 1449b32609ca7189 451c259d65bd15b3"
              + "4d8729afdb5faff8 af1f3437bbdc0c3d 0b069a8ab2a959c9"
              + "0c5a43d42082c774 90f3afcc10ef5648 625c0605cdaace6c"
              + "6fdc9e9a7e6635d6 19f50af773452247 0502cab26a52a198"
              + "f5b00a2798589165 07b0b4e9ef9524d6"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
