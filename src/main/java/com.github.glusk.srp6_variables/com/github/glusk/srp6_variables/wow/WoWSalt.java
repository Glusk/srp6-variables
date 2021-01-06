package com.github.glusk.srp6_variables.wow;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

/** WoW Test Vector: salt (s). */
public final class WoWSalt implements Bytes {
    /** Pre-set constant that represents this variable. */
    private static final Bytes VALUE =
        new Hex(
            "8598916D 316FF815 3C23AE77 CE67009C"
          + "683FD10D F8F66F6E 96050C87 0208DB16"
        );

    @Override
    public byte[] asArray() {
        return VALUE.asArray();
    }
}
