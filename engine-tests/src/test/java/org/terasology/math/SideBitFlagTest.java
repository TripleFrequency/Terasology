package org.terasology.math;

import com.google.api.client.util.Lists;
import com.google.common.collect.Sets;
import gnu.trove.map.TObjectByteMap;
import gnu.trove.map.hash.TObjectByteHashMap;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SideBitFlagTest {
    private SideBitFlag sut;
    private static TObjectByteMap<Side> sideBits = new TObjectByteHashMap<>();
    private Set<Side> sideSet;
    private Side s1;
    private Side s2;
    private Side s3;
    private byte b1;

    @Before
    public void setup() {
        sut = new SideBitFlag();
        sideSet = new HashSet<>();
    }

    @Test
    public void givenByte_whenGetReverse_thenReturnReverse() {
        byte reverse = SideBitFlag.getReverse((byte) 4);
        assertEquals((byte) (((4 % 8) * 8)), reverse);
    }

    @Test
    public void givenSetOfSides_whenGetSides_thenReturnByte() throws Exception {
        byte result1 = SideBitFlag.getSides(sideSet);
        byte expected1 = 0;
        for (Side side : sideSet) {
            expected1 += sideBits.get(side);
        }
        assertEquals(expected1, result1);
        byte result2 = SideBitFlag.getSides(s1, s2, s3);
        byte expected2 = 0;
        Set<Side> sideSet = new HashSet<>();
        sideSet.add(s1);
        sideSet.add(s2);
        sideSet.add(s3);
        for (Side side : sideSet) {
            final byte sideBit = sideBits.get(side);
            if ((expected2 & sideBit) > 0) {
                throw new IllegalArgumentException("Cannot have multiples of the same side");
            }
            expected2 += sideBit;
        }
        assertEquals(expected2, result2);
    }

    @Test
    public void givenSide_whenGetSide_thenReturnSide() {
        byte expected = sideBits.get(s1);
        assertEquals(expected, SideBitFlag.getSide(s1));
    }

    @Test
    public void givenSidesBit_whenGetSides_thenReturnEnumSetOfSides() {
        List<Side> expected = Lists.newArrayList();
        sideBits.forEachEntry((a, b) -> {
            if ((b & b1) > 0) {
                expected.add(a);
            }
            return true;
        });
        assertEquals(Sets.newEnumSet(expected, Side.class), SideBitFlag.getSides(b1));
    }
}
