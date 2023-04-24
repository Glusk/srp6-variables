package com.github.glusk.srp6_variables.wiki;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Wikipedia Python example variable: premaster secret (S). */
public final class WikiPremasterSecret extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "94:ea:4b:72:b6:1d:43:30:cf:44:f3:1e:5c:71:04:"
              + "91:d4:1a:bd:d6:dd:5b:66:b2:77:bc:51:7a:dd:be:"
              + "89:d9:aa:00:26:45:89:75:67:ae:77:96:d1:57:4f:"
              + "5d:7f:62:cf:96:d2:24:6d:ab:fb:c9:19:cf:14:44:"
              + "d6:90:97:ce:af:54:76:bc:39:64:ca:cd:52:69:7e:"
              + "34:6f:5e:5a:42:4c:2c:89:b6:61:d2:eb:a3:4e:5c:"
              + "71:95:57:34:42:19:56:11:49:7f:60:6f:a4:96:39:"
              + "f8:73:f3:85:d0:f6:cd:b9:30:8f:e2:b0:77:7d:1a:"
              + "89:bb:ae:be:9d:f2:37:a4"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
