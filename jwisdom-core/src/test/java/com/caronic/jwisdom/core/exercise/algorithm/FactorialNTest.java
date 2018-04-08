package com.caronic.jwisdom.core.exercise.algorithm;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by nocoh on 2018/2/3.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class FactorialNTest extends TestCase {

    @Test
    public void testRun() throws Exception {
        Integer input = 5;
        Integer expectResult = 5*4*3*2*1;

        FactorialN factorialN = new FactorialN();
        Integer actualResult = factorialN.run(input);
        assertEquals(expectResult, actualResult);

        input = 1;
        expectResult = 1;
        actualResult = factorialN.run(input);
        assertEquals(expectResult, actualResult);

        input = 0;
        expectResult = 1;
        actualResult = factorialN.run(input);
        assertEquals(expectResult, actualResult);
    }
}