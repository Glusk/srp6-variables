package com.github.glusk.srp6_variables.wiki;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/**
 * Wikipedia Python example variable: private key (x).
 * <p>
 * The example on Wikipedia computes this variable in an unusual way:
 * <pre>
 * x = H(s | ":" | I | ":" | P)
 * x = H("2577509697835434688:person:password1234")
 * </pre>
 */
public final class WikiPrivateKey extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
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
