package org.terasology.math;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.terasology.math.geom.Vector3f;

import java.rmi.server.ExportException;
import java.util.Vector;

public class Vector3fUtilTest {
    private Vector3f direction;
    private Vector3f normal;
    private Vector3f out;
    private Vector3f a;
    private Vector3f b;

    @Before
    public void setUp() throws Exception {
        direction = new Vector3f(1, 2, 3);
        normal = new Vector3f(4, 5, 6);
        out = new Vector3f(7, 8, 9);
        a = direction;
        b = normal;

    }

    @Test
    public void testVector3fReflect() throws Exception {
        Vector3f reflected = Vector3fUtil.reflect(direction, normal, out);
        Vector3f expected = out.set(normal).scale(-2.0f * direction.dot(normal)).add(direction);
        assertEquals(expected.x, reflected.x, 0.0001);
        assertNotEquals(expected.y, reflected.z, 0.0001);
    }

    @Test
    public void testVector3fGetParallelComponent() throws Exception {
        double parallelX = Vector3fUtil.getParallelComponent(direction, normal, out).x;
        double expectedX = out.set(normal).scale(direction.dot(normal)).x;
        assertEquals(expectedX, parallelX, 0.0001);
    }

    @Test
    public void testVector3fGetPerpendicularComponent() {
        double perpendicularZ = Vector3fUtil.getPerpendicularComponent(direction, normal, out).z;
        double expectedZ = Vector3fUtil.getParallelComponent(direction, normal, out).scale(-1).add(direction).z;
        assertEquals(expectedZ, perpendicularZ, 0.0001);
    }

    @Test
    public void testVector3fSafeNormalize() throws Exception {
        double safeNormalizeY = Vector3fUtil.safeNormalize(direction, out).y;
        Vector3f expected = out.set(direction).normalize();
        if (expected.length() < 0.000001f) {
            out.set(0, 0, 0);
        }
        double expectedY = expected.y;
        assertEquals(expectedY, safeNormalizeY, 0.0001);
    }

    @Test
    public void testVector3fMin() throws Exception {
        Vector3f min = Vector3fUtil.min(a, b, out);
        Vector3f expected = out;
        expected.x = Math.min(a.x, b.x);
        expected.y = Math.min(a.y, b.y);
        expected.z = Math.min(a.z, b.z);
        assertEquals(expected.y, min.y, 0.0001);
        assertNotEquals(expected.z, min.x, 0.0001);
    }

    @Test
    public void testVector3fMax() throws Exception {
        Vector3f max = Vector3fUtil.max(a, b, out);
        Vector3f expected = out;
        expected.x = Math.max(a.x, b.x);
        expected.y = Math.max(a.y, b.y);
        expected.z = Math.max(a.z, b.z);
        assertEquals(expected.y, max.y, 0.0001);
        assertNotEquals(expected.z, max.x, 0.0001);
    }
}
