package com.github.glusk.srp6_variables.wiki;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6PresetIntegerVariable;

/** Wikipedia Python example variable: verifier (v). */
public final class WikiVerifier implements SRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6PresetIntegerVariable(
            new Hex(
                "a6:36:25:44:92:ec:0f:73:91:d6:b5:96:ec:92:6b:"
              + "91:86:67:75:07:2d:fd:75:8c:6e:bc:51:bf:72:77:"
              + "ec:6c:a9:7f:6c:f0:31:6d:7f:a9:0a:2b:9e:87:36:"
              + "6c:f8:13:a5:3d:cd:c6:ab:30:3f:cc:93:2a:57:83:"
              + "f6:2a:ff:b7:e0:27:51:89:f1:65:b8:b9:19:a2:06:"
              + "74:04:e6:f2:aa:05:34:c9:9a:32:24:a6:36:5c:13:"
              + "67:dc:d9:ef:00:53:76:d6:f2:0a:2b:30:0c:30:7f:"
              + "7a:fc:ed:ea:08:fb:2d:7a:33:40:f1:3b:5b:9e:35:"
              + "d5:2f:0b:82:67:0a:b1:7e"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
