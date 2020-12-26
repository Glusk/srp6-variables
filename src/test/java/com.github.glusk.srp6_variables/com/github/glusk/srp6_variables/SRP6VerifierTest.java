package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.github.glusk.srp6_variables.rfc5054.RFC5054Generator;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;
import com.github.glusk.srp6_variables.rfc5054.RFC5054PrivateKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Verifier;

public final class SRP6VerifierTest {
    @Test
    public void testAgainstRFC5054TestVectors() {
        assertTrue(
            new RFC5054Verifier().equals(
                new SRP6Verifier(
                    new RFC5054Prime(),
                    new RFC5054Generator(),
                    new RFC5054PrivateKey()
                )
            ),
            "Computed variable does not match the RFC5054 Test Vector"
        );
    }
}
