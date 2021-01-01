package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.github.glusk.srp6_variables.rfc5054.RFC5054ClientPrivateEphemeral;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ClientPublicKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Generator;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;

public final class SRP6ClientPublicKeyTest {
    @Test
    public void testAgainstRFC5054TestVectors() {
        assertTrue(
            new RFC5054ClientPublicKey().equals(
                new SRP6ClientPublicKey(
                    new RFC5054Prime(),
                    new RFC5054Generator(),
                    new RFC5054ClientPrivateEphemeral()
                )
            ),
            "Computed variable does not match the RFC5054 Test Vector"
        );
    }
}
