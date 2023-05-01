package com.github.glusk.srp6_variables;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.PlainText;
import com.github.glusk.caesar.hashing.Hash;
import com.github.glusk.caesar.hashing.ImmutableMessageDigest;

import com.github.glusk.srp6_variables.rfc5054.RFC5054ClientPublicKey;
import com.github.glusk.srp6_variables.rfc5054.RFC5054Prime;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ScramblingParameter;
import com.github.glusk.srp6_variables.rfc5054.RFC5054ServerPublicKey;

import com.github.glusk.srp6_variables.mozilla.MozillaClientPublicKey;
import com.github.glusk.srp6_variables.mozilla.MozillaPrime;
import com.github.glusk.srp6_variables.mozilla.MozillaScramblingParameter;
import com.github.glusk.srp6_variables.mozilla.MozillaServerPublicKey;

import com.github.glusk.srp6_variables.wiki.WikiClientPublicKey;
import com.github.glusk.srp6_variables.wiki.WikiScramblingParameter;
import com.github.glusk.srp6_variables.wiki.WikiServerPublicKey;

import com.github.glusk.srp6_variables.wow.WoWClientPublicKey;
import com.github.glusk.srp6_variables.wow.WoWPrime;
import com.github.glusk.srp6_variables.wow.WoWScramblingParameter;
import com.github.glusk.srp6_variables.wow.WoWServerPublicKey;

public final class SRP6ScramblingParameterTest {
    @Test
    public void testAgainstRFC5054TestVectors()
        throws NoSuchAlgorithmException {
        assertEquals(
            new RFC5054ScramblingParameter(),
            new SRP6ScramblingParameter(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-1")
                ),
                new RFC5054ClientPublicKey(),
                new RFC5054ServerPublicKey(),
                new RFC5054Prime(),
                ByteOrder.BIG_ENDIAN
            ),
            "Computed variable does not match the RFC5054 Test Vector"
        );
    }
    @Test
    public void testAgainstMozillaTestVectors()
        throws NoSuchAlgorithmException {
        assertEquals(
            new MozillaScramblingParameter(),
            new SRP6ScramblingParameter(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-256")
                ),
                new MozillaClientPublicKey(),
                new MozillaServerPublicKey(),
                new MozillaPrime(),
                ByteOrder.BIG_ENDIAN
            ),
            "Computed variable does not match the Mozilla Test Vector"
        );
    }
    @Test
    public void testAgainstWikipediaExampleVariables()
        throws NoSuchAlgorithmException {
        assertEquals(
            new WikiScramblingParameter(),
            new SRP6CustomIntegerVariable(
                new Hash(
                    new ImmutableMessageDigest(
                        MessageDigest.getInstance("SHA-256")
                    ),
                    new PlainText(
                        new WikiClientPublicKey()
                            .asNonNegativeBigInteger()
                        + ":"
                        + new WikiServerPublicKey()
                            .asNonNegativeBigInteger()
                    )
                ),
                ByteOrder.BIG_ENDIAN
            ),
            "Computed variable does not match the Wikipedia example variable"
        );
    }
    @Test
    public void testAgainstWoWTestVectors()
        throws NoSuchAlgorithmException {
        assertEquals(
            new WoWScramblingParameter(),
            new SRP6ScramblingParameter(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-1")
                ),
                new WoWClientPublicKey(),
                new WoWServerPublicKey(),
                new WoWPrime(),
                ByteOrder.LITTLE_ENDIAN
            ),
            "Computed variable does not match the WoW Test Vector"
        );
    }
}
