package com.github.glusk.srp6_variables.mozilla;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;

import com.github.glusk.srp6_variables.AbstractSRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6IntegerVariable;
import com.github.glusk.srp6_variables.SRP6CustomIntegerVariable;

/** Mozilla Test Vector: server public key (B). */
public final class MozillaServerPublicKey
    extends AbstractSRP6IntegerVariable {
    /** Pre-set constant that represents this variable. */
    private static final SRP6IntegerVariable VALUE =
        new SRP6CustomIntegerVariable(
            new Hex(
                "0022ce5a7b9d8127 7172caa20b0f1efb 4643b3becc535664"
              + "73959b07b790d3c3 f08650d5531c19ad 30ebb67bdb481d1d"
              + "9cf61bf272f84398 48fdda58a4e6abc5 abb2ac496da5098d"
              + "5cbf90e29b4b110e 4e2c033c70af7392 5fa37457ee13ea3e"
              + "8fde4ab516dff1c2 ae8e57a6b264fb9d b637eeeae9b5e43d"
              + "faba9b329d3b8770 ce89888709e02627 0e474eef822436e6"
              + "397562f284778673 a1a7bc12b6883d1c 21fbc27ffb3dbeb8"
              + "5efda279a69a1941 4969113f10451603 065f0a0126666456"
              + "51dde44a52f4d8de 113e2131321df1bf 4369d2585364f9e5"
              + "36c39a4dce33221b e57d50ddccb4384e 3612bbfd03a268a3"
              + "6e4f7e01de651401 e108cc247db50392"
            ),
            ByteOrder.BIG_ENDIAN
        );

    @Override
    public Bytes bytes(final ByteOrder preferredOrder) {
        return VALUE.bytes(preferredOrder);
    }
}
