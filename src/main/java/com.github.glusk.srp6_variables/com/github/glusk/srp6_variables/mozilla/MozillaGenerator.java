package com.github.glusk.srp6_variables.mozilla;

import java.math.BigInteger;
import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;

import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/**
 * Mozilla Test Vector: generator (g).
 * <p>
 * A 2048-bit group from RFC 5054, Appendix A.
 */
public final class MozillaGenerator implements SRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    @SuppressWarnings("checkstyle:linelength")
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            BigInteger.valueOf(2)
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
