package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.github.glusk.srp6_variables.rfc5054.RFC5054Generator;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Multiplier;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ServerPrivateEphemeral;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ServerPublicKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Verifier;

import com.github.glusk.srp6_variables.wow.WoWGenerator;
import com.github.glusk.srp6_variables.wow.WoWMultiplier;
import com.github.glusk.srp6_variables.wow.WoWServerPrivateEphemeral;
import com.github.glusk.srp6_variables.wow.WoWServerPublicKey;
import com.github.glusk.srp6_variables.wow.WoWPrime;
import com.github.glusk.srp6_variables.wow.WoWVerifier;

public final class SRP6ServerPublicKeyTest {
    @Test
    public void testAgainstRFC5054TestVectors() {
        assertTrue(
            new RFC5054ServerPublicKey().equals(
                new SRP6ServerPublicKey(
                    new RFC5054Prime(),
                    new RFC5054Generator(),
                    new RFC5054Multiplier(),
                    new RFC5054Verifier(),
                    new RFC5054ServerPrivateEphemeral()
                )
            ),
            "Computed variable does not match the RFC5054 Test Vector"
        );
    }
    @Test
    public void testAgainstWoWTestVectors() {
        assertTrue(
            new WoWServerPublicKey().equals(
                new SRP6ServerPublicKey(
                    new WoWPrime(),
                    new WoWGenerator(),
                    new WoWMultiplier(),
                    new WoWVerifier(),
                    new WoWServerPrivateEphemeral()
                )
            ),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
