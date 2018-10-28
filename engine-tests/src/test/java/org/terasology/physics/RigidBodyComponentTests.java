package org.terasology.physics;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.terasology.math.geom.Vector3f;
import org.terasology.physics.components.RigidBodyComponent;

import java.util.ArrayList;

public class RigidBodyComponentTests {
    private static RigidBodyComponent sut;

    @BeforeClass
    public static void setup() {
        sut = new RigidBodyComponent();
    }

    @Test
    public void givenRigidBodyComponent_whenJustCreated_shouldHaveDefaultValues() {
        Assert.assertEquals(sut.mass, 10.0f, 0.001);
        Assert.assertEquals(sut.angularFactor, new Vector3f(1f, 1f, 1f));
        Assert.assertEquals(sut.linearFactor, new Vector3f(1f, 1f, 1f));
        Assert.assertEquals(sut.friction, 0.5f, 0.001);
        Assert.assertEquals(sut.restitution, 0f, 0.001);
        Assert.assertEquals(sut.collisionGroup, StandardCollisionGroup.DEFAULT);
    }

    @Test
    public void givenRigidBodyComponent_whenCollisionTypeGiven_thenShouldOnlyCollideWithDefaultWorldOrKinematic() {
        ArrayList<CollisionGroup> expectedCollidesWith = new ArrayList<>();
        expectedCollidesWith.add(StandardCollisionGroup.DEFAULT);
        expectedCollidesWith.add(StandardCollisionGroup.WORLD);
        expectedCollidesWith.add(StandardCollisionGroup.KINEMATIC);
        Assert.assertEquals(sut.collidesWith, expectedCollidesWith);
    }
}
