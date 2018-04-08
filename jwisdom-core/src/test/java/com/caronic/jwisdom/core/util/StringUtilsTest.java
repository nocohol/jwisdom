package com.caronic.jwisdom.core.util;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by nocoh on 2018/2/3.
 */
public class StringUtilsTest extends TestCase {

    @Test
    public void testReverseWithSpace() throws Exception {
        String input = null;
        String expectResult = "";
        String actualResult = StringUtils.reverseWithSpace(input);
        assertEquals(expectResult, actualResult);

        input = "";
        expectResult = "";
        actualResult = StringUtils.reverseWithSpace(input);
        assertEquals(expectResult, actualResult);

        input = "a";
        expectResult = "a";
        actualResult = StringUtils.reverseWithSpace(input);
        assertEquals(expectResult, actualResult);

        input = "abcdef";
        expectResult = "f e d c b a";
        actualResult = StringUtils.reverseWithSpace(input);
        assertEquals(expectResult, actualResult);

        input = null;
        expectResult = "";
        actualResult = StringUtils.reverseWithSpace2(input);
        assertEquals(expectResult, actualResult);

        input = "";
        expectResult = "";
        actualResult = StringUtils.reverseWithSpace2(input);
        assertEquals(expectResult, actualResult);

        input = "a";
        expectResult = "a";
        actualResult = StringUtils.reverseWithSpace2(input);
        assertEquals(expectResult, actualResult);

        input = "ab";
        expectResult = "b a";
        actualResult = StringUtils.reverseWithSpace2(input);
        assertEquals(expectResult, actualResult);

        input = "abcdef";
        expectResult = "f e d c b a";
        actualResult = StringUtils.reverseWithSpace2(input);
        assertEquals(expectResult, actualResult);
    }
}