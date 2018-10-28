package org.terasology.physics;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.terasology.context.Context;
import org.terasology.context.internal.ContextImpl;
import org.terasology.engine.bootstrap.EntitySystemSetupUtil;
import org.terasology.engine.module.ModuleManager;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.math.geom.Vector3f;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.testUtil.ModuleManagerFactory;

public class HitResultTests {

    private static EntityRef testEntity;
    private Vector3f testHitPoint = new Vector3f(1.0f, 1.0f, 1.0f);
    private Vector3f testHitNormal = new Vector3f(5.0f, 5.0f, 5.0f);

    @BeforeClass
    static public void setup() throws Exception {
        Context testContext = new ContextImpl();
        EntityManager testManager;
        ModuleManager moduleManager = ModuleManagerFactory.create();
        testContext.put(ModuleManager.class, moduleManager);
        CoreRegistry.setContext(testContext);
        EntitySystemSetupUtil.addReflectionBasedLibraries(testContext);
        EntitySystemSetupUtil.addEntityManagementRelatedClasses(testContext);
        testManager = testContext.get(EntityManager.class);
        testEntity = testManager.create();
    }

    @Test
    public void givenHitResult_whenDefaultConstructorCalled_ShouldReturnHitNothingResult() {
        HitResult sut = new HitResult();
        Assert.assertFalse(sut.isHit());
        Assert.assertEquals(sut.getEntity(), EntityRef.NULL);
        Assert.assertFalse(sut.isWorldHit());
    }

    @Test
    public void givenHitResult_whenGivenAnEntityAndVectors_thenShouldReturnEntityHitResult() {
        HitResult sut = new HitResult(testEntity,testHitPoint, testHitNormal);
        Assert.assertTrue(sut.isHit());
        Assert.assertFalse(sut.isWorldHit());
        Assert.assertEquals(sut.getHitPoint(), testHitPoint);
        Assert.assertEquals(sut.getHitNormal(), testHitNormal);
    }

    @Test
    public void givenHitResult_whenGivenEntityAndVectorsAndBlockPos_thenShouldReturnABlockHitResult() {
        Vector3i testBlockPos = new Vector3i(10, 10, 10);
        HitResult sut = new HitResult(testEntity, testHitPoint, testHitNormal, testBlockPos);
        Assert.assertTrue(sut.isHit());
        Assert.assertTrue(sut.isWorldHit());
        Assert.assertEquals(sut.getHitPoint(), testHitPoint);
        Assert.assertEquals(sut.getHitNormal(), testHitNormal);
        Assert.assertEquals(sut.getBlockPosition(), testBlockPos);
    }
}
