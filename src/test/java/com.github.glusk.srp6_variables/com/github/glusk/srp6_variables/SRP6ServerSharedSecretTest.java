package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import com.github.glusk.srp6_variables.rfc5054.RFC5054ClientPublicKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054PremasterSecret;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ServerPrivateEphemeral;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ScramblingParameter;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Verifier;

public final class SRP6ServerSharedSecretTest {
    @Test
    public void testAgainstRFC5054TestVectors() throws SRP6SecurityException {
        assertTrue(
            new RFC5054PremasterSecret().equals(
                new SRP6ServerSharedSecret(
                    new RFC5054Prime(),
                    new RFC5054ClientPublicKey(),
                    new RFC5054Verifier(),
                    new RFC5054ScramblingParameter(),
                    new RFC5054ServerPrivateEphemeral()
                )
            ),
            "Computed variable does not match the RFC5054 Test Vector"
        );
    }
    @Test
    public void failsIfClientPublicKeyIsZero() {
        assertThrows(
            SRP6SecurityException.class,
            () -> {
                new SRP6ServerSharedSecret(
                    new RFC5054Prime(),
                    new SRP6CustomIntegerVariable(
                        BigInteger.ZERO
                    ),
                    new RFC5054Verifier(),
                    new RFC5054ScramblingParameter(),
                    new RFC5054ServerPrivateEphemeral()
                );
            }
        );
    }
     @Test
    public void failsIfClientPublicKeyEqualsToPrime() {
        assertThrows(
            SRP6SecurityException.class,
            () -> {
                new SRP6ServerSharedSecret(
                    new RFC5054Prime(),
                    new RFC5054Prime(),
                    new RFC5054Verifier(),
                    new RFC5054ScramblingParameter(),
                    new RFC5054ServerPrivateEphemeral()
                );
            }
        );
    }
}
