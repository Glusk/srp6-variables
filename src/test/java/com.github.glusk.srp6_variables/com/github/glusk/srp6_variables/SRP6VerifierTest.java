package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.github.glusk.srp6_variables.rfc5054.RFC5054Generator;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;
import com.github.glusk.srp6_variables.rfc5054.RFC5054PrivateKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Verifier;

import com.github.glusk.srp6_variables.mozilla.MozillaGenerator;
import com.github.glusk.srp6_variables.mozilla.MozillaPrime;
import com.github.glusk.srp6_variables.mozilla.MozillaPrivateKey;
import com.github.glusk.srp6_variables.mozilla.MozillaVerifier;

import com.github.glusk.srp6_variables.wiki.WikiGenerator;
import com.github.glusk.srp6_variables.wiki.WikiPrime;
import com.github.glusk.srp6_variables.wiki.WikiPrivateKey;
import com.github.glusk.srp6_variables.wiki.WikiVerifier;

import com.github.glusk.srp6_variables.wow.WoWGenerator;
import com.github.glusk.srp6_variables.wow.WoWPrime;
import com.github.glusk.srp6_variables.wow.WoWPrivateKey;
import com.github.glusk.srp6_variables.wow.WoWVerifier;

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
    @Test
    public void testAgainstMozillaTestVectors() {
        assertTrue(
            new MozillaVerifier().equals(
                new SRP6Verifier(
                    new MozillaPrime(),
                    new MozillaGenerator(),
                    new MozillaPrivateKey()
                )
            ),
            "Computed variable does not match the Mozilla Test Vector"
        );
    }
    @Test
    public void testAgainstWikipediaExampleVariables() {
        assertTrue(
            new WikiVerifier().equals(
                new SRP6Verifier(
                    new WikiPrime(),
                    new WikiGenerator(),
                    new WikiPrivateKey()
                )
            ),
            "Computed variable does not match the Wikipedia example variable"
        );
    }
    @Test
    public void testAgainstWoWTestVectors() {
        assertTrue(
            new WoWVerifier().equals(
                new SRP6Verifier(
                    new WoWPrime(),
                    new WoWGenerator(),
                    new WoWPrivateKey()
                )
            ),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
