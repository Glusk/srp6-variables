package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import com.github.glusk.srp6_variables.rfc5054.RFC5054ServerPublicKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054PremasterSecret;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Generator;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Multiplier;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ClientPrivateEphemeral;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ScramblingParameter;
import com.github.glusk.srp6_variables.rfc5054.RFC5054PrivateKey;

public final class SRP6ClientSharedSecretTest {
    @Test
    public void testAgainstRFC5054TestVectors() throws SRP6SecurityException {
        assertTrue(
            new RFC5054PremasterSecret().equals(
                new SRP6ClientSharedSecret(
                    new RFC5054Prime(),
                    new RFC5054Generator(),
                    new RFC5054Multiplier(),
                    new RFC5054ServerPublicKey(),
                    new RFC5054PrivateKey(),
                    new RFC5054ScramblingParameter(),
                    new RFC5054ClientPrivateEphemeral()
                )
            ),
            "Computed variable does not match the RFC5054 Test Vector"
        );
    }
    @Test
    public void failsIfServerPublicKeyIsZero() {
        assertThrows(
            SRP6SecurityException.class,
            () -> {
                new SRP6ClientSharedSecret(
                    new RFC5054Prime(),
                    new RFC5054Generator(),
                    new RFC5054Multiplier(),
                    new SRP6CustomIntegerVariable(
                        BigInteger.ZERO
                    ),
                    new RFC5054PrivateKey(),
                    new RFC5054ScramblingParameter(),
                    new RFC5054ClientPrivateEphemeral()
                );
            }
        );
    }
    @Test
    public void failsIfRandomScramblingParameterIsZero() {
        assertThrows(
            SRP6SecurityException.class,
            () -> {
                new SRP6ClientSharedSecret(
                    new RFC5054Prime(),
                    new RFC5054Generator(),
                    new RFC5054Multiplier(),
                    new RFC5054ServerPublicKey(),
                    new RFC5054PrivateKey(),
                    new SRP6CustomIntegerVariable(
                        BigInteger.ZERO
                    ),
                    new RFC5054ClientPrivateEphemeral()
                );
            }
        );
    }
}
