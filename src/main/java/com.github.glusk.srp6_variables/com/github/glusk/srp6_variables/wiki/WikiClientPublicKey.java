package com.github.glusk.srp6_variables.wiki;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Wikipedia Python example variable: client ephemeral public key (A). */
public final class WikiClientPublicKey implements SRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "48:14:7d:01:3e:3a:2e:08:ac:e2:22:a0:ab:91:4a:"
              + "7e:d6:7c:70:4b:24:80:71:6b:53:f9:d2:29:24:3d:"
              + "17:25:47:3c:f4:45:19:04:65:85:97:f4:87:b0:fa:"
              + "8b:c7:d5:44:67:1b:25:56:3f:09:5b:ad:38:4c:bb:"
              + "8d:a7:f5:8f:7f:13:c8:fa:8b:b9:d6:aa:de:5f:e0:"
              + "2d:f2:88:f2:b3:8d:71:d5:10:36:ed:e5:28:02:64:"
              + "5f:82:cd:72:16:53:5c:0c:97:8f:90:23:0e:0f:87:"
              + "81:63:a6:38:cf:57:ad:11:96:81:69:c2:6e:46:7b:"
              + "8e:e1:4e:b2:ca:5b:16:14"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
