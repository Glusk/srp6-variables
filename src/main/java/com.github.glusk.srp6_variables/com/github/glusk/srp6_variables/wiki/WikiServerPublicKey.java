package com.github.glusk.srp6_variables.wiki;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Wikipedia Python example variable: server ephemeral public key (B). */
public final class WikiServerPublicKey extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "70:9f:34:07:38:e6:2e:46:18:46:34:ac:d2:cd:7c:"
              + "86:1a:7d:92:c5:fd:e9:eb:43:ac:12:02:26:a0:eb:"
              + "66:01:ee:5d:1a:0b:92:ff:b6:25:41:70:d9:1f:b4:"
              + "51:c3:c0:2b:bf:8b:41:f9:e7:e3:e8:85:d7:09:f0:"
              + "dc:48:08:04:8e:59:5c:68:44:8a:21:11:b4:5e:ef:"
              + "aa:1e:2d:6a:48:14:d9:9a:e0:38:a5:f2:37:1c:75:"
              + "3b:31:2c:52:9c:ad:a6:6b:23:e6:51:2c:7e:f8:14:"
              + "68:3f:4c:fe:2a:4c:54:13:c4:34:e2:1b:c6:89:d8:"
              + "69:fc:96:91:41:b8:4a:61"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
