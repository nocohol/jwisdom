package com.caronic.jwisdom.core.exercise.base.java8.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by caronic on 2016/10/15.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class MyFunctionalImplTest {

    @Test
    public void testMethod() {
        FunctionalInterfaceUsage usage = new FunctionalInterfaceUsage((string) -> System.out.println(string));
        usage.method("test");
    }

}
