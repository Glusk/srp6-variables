package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.nio.ByteOrder;

import com.github.glusk.srp6_variables.rfc5054.RFC5054ClientPublicKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054PremasterSecret;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ServerPrivateEphemeral;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ScramblingParameter;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Verifier;

import com.github.glusk.srp6_variables.mozilla.MozillaClientPublicKey;
import com.github.glusk.srp6_variables.mozilla.MozillaSharedSecret;
import com.github.glusk.srp6_variables.mozilla.MozillaPrime;
import com.github.glusk.srp6_variables.mozilla.MozillaServerPrivateEphemeral;
import com.github.glusk.srp6_variables.mozilla.MozillaScramblingParameter;
import com.github.glusk.srp6_variables.mozilla.MozillaVerifier;

import com.github.glusk.srp6_variables.wow.WoWClientPublicKey;
import com.github.glusk.srp6_variables.wow.WoWPremasterSecret;
import com.github.glusk.srp6_variables.wow.WoWPrime;
import com.github.glusk.srp6_variables.wow.WoWServerPrivateEphemeral;
import com.github.glusk.srp6_variables.wow.WoWScramblingParameter;
import com.github.glusk.srp6_variables.wow.WoWVerifier;

public final class SRP6ServerSharedSecretTest {
    @Test
    public void testAgainstRFC5054TestVectors() {
        assertEquals(
            new RFC5054PremasterSecret(),
            new SRP6ServerSharedSecret(
                new RFC5054Prime(),
                new RFC5054ClientPublicKey(),
                new RFC5054Verifier(),
                new RFC5054ScramblingParameter(),
                new RFC5054ServerPrivateEphemeral()
            ),
            "Computed variable does not match the RFC5054 Test Vector"
        );
    }
    @Test
    public void testAgainstMozillaTestVectors() {
        assertEquals(
            new MozillaSharedSecret(),
            new SRP6ServerSharedSecret(
                new MozillaPrime(),
                new MozillaClientPublicKey(),
                new MozillaVerifier(),
                new MozillaScramblingParameter(),
                new MozillaServerPrivateEphemeral()
            ),
            "Computed variable does not match the Mozilla Test Vector"
        );
    }
    @Test
    public void failsIfClientPublicKeyIsZero() {
        assertThrows(
            IllegalStateException.class,
            () -> {
                new SRP6ServerSharedSecret(
                    new RFC5054Prime(),
                    new SRP6CustomIntegerVariable(
                        BigInteger.ZERO
                    ),
                    new RFC5054Verifier(),
                    new RFC5054ScramblingParameter(),
                    new RFC5054ServerPrivateEphemeral()
                ).bytes(ByteOrder.BIG_ENDIAN);
            }
        );
    }
    @Test
    public void failsIfClientPublicKeyEqualsToPrime() {
        assertThrows(
            IllegalStateException.class,
            () -> {
                new SRP6ServerSharedSecret(
                    new RFC5054Prime(),
                    new RFC5054Prime(),
                    new RFC5054Verifier(),
                    new RFC5054ScramblingParameter(),
                    new RFC5054ServerPrivateEphemeral()
                ).bytes(ByteOrder.BIG_ENDIAN);
            }
        );
    }
    @Test
    public void testAgainstWoWTestVectors() {
        assertEquals(
            new WoWPremasterSecret(),
            new SRP6ServerSharedSecret(
                new WoWPrime(),
                new WoWClientPublicKey(),
                new WoWVerifier(),
                new WoWScramblingParameter(),
                new WoWServerPrivateEphemeral()
            ),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
