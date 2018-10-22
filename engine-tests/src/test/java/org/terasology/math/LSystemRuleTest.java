package org.terasology.math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LSystemRuleTest {
    private LSystemRule sut;

    @Before
    public void setup() {
        sut = new LSystemRule("peg", 0.85f);
    }

    @Test
    public void givenLSystemRuleExists_whenGetProperty_thenReturnProperty() {
        assertEquals("peg", sut.getAxiom());
        assertEquals(0.85, sut.getProbability(), 0.0001);
    }
}
