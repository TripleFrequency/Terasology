package org.terasology.math;

import org.junit.Before;
import org.junit.Test;
import org.terasology.math.geom.Matrix3f;
import org.terasology.math.geom.Quat4f;
import org.terasology.math.geom.Vector3f;

import static org.junit.Assert.assertEquals;

public class TransformTest {
    private Vector3f v;
    private Quat4f q;
    private Transform sut;

    @Before
    public void setup() {
        v = new Vector3f();
        q = new Quat4f();
        sut = new Transform(v, q, 0.999f);
    }

    @Test
    public void givenTransformExists_whenGetBasis_thenReturnBasis() {
        Matrix3f expected = new Matrix3f();
        expected.set(q);
        expected.mul(0.999f);
        assertEquals(expected, sut.getBasis());
    }

    @Test
    public void givenVector3f_whenTransform_thenReturnTransformedVector() {
        Vector3f expected = v;
        sut.getBasis().transform(expected);
        expected.add(v);
        sut.transform(v);
        assertEquals(expected, v);
    }
}
