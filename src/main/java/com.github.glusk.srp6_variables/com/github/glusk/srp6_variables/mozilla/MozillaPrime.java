package com.github.glusk.srp6_variables.mozilla;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/**
 * Mozilla Test Vector: prime (N).
 * <p>
 * A 2048-bit value from RFC 5054, Appendix A.
 */
public final class MozillaPrime extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    @SuppressWarnings("checkstyle:linelength")
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "AC6BDB41 324A9A9B F166DE5E 1389582F AF72B665 1987EE07 FC319294"
              + "3DB56050 A37329CB B4A099ED 8193E075 7767A13D D52312AB 4B03310D"
              + "CD7F48A9 DA04FD50 E8083969 EDB767B0 CF609517 9A163AB3 661A05FB"
              + "D5FAAAE8 2918A996 2F0B93B8 55F97993 EC975EEA A80D740A DBF4FF74"
              + "7359D041 D5C33EA7 1D281E44 6B14773B CA97B43A 23FB8016 76BD207A"
              + "436C6481 F1D2B907 8717461A 5B9D32E6 88F87748 544523B5 24B0D57D"
              + "5EA77A27 75D2ECFA 032CFBDB F52FB378 61602790 04E57AE6 AF874E73"
              + "03CE5329 9CCC041C 7BC308D8 2A5698F3 A8D0C382 71AE35F8 E9DBFBB6"
              + "94B5C803 D89F7AE4 35DE236D 525F5475 9B65E372 FCD68EF2 0FA7111F"
              + "9E4AFF73"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
