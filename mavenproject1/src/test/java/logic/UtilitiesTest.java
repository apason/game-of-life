package logic;

import java.util.ArrayList;
import logic.Utilities;
import static logic.Utilities.correctConditionList;
import static logic.Utilities.correctIterations;
import static logic.Utilities.correctSize;
import static logic.Utilities.correctSteptime;
import static logic.Utilities.listToString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author apa
 */
public class UtilitiesTest {

    @Test
    public void correctFilenameWorksProperly() {
        assertEquals("testfilename.dat", Utilities.correctFilename("testfilename.dat"));
        assertEquals("testfilename.dat", Utilities.correctFilename("testfilename"));
    }

    @Test
    public void correctConditionListWorksProperly() {
        assertTrue(correctConditionList("1,2,3,4"));
        assertFalse(correctConditionList("1,2,3,4,"));
        assertFalse(correctConditionList("100"));
        assertTrue(correctConditionList("10"));
        assertFalse(correctConditionList("1,3,b,5"));
        assertTrue(correctConditionList("5,10,66"));
        assertTrue(correctConditionList("0,9,10,29,20,99"));
    }

    @Test
    public void correctSizeWorksProperly() {
        assertTrue(correctSize("3"));
        assertTrue(correctSize("2"));
        assertTrue(correctSize("9"));
        assertTrue(correctSize("10"));
        assertTrue(correctSize("1999"));
        assertTrue(correctSize("999"));
        assertTrue(correctSize("1000"));
        assertTrue(correctSize("99"));
        assertTrue(correctSize("100"));
        assertFalse(correctSize("2000"));
        assertFalse(correctSize("0"));
        assertFalse(correctSize("1"));
        assertFalse(correctSize("-5"));
        assertFalse(correctSize("3000"));
        assertFalse(correctSize("2001"));
    }

    @Test
    public void correctSteptimeWorksProperly() {
        assertTrue(correctSteptime("1"));
        assertTrue(correctSteptime("9"));
        assertTrue(correctSteptime("10"));
        assertTrue(correctSteptime("99"));
        assertTrue(correctSteptime("100"));
        assertTrue(correctSteptime("999"));
        assertTrue(correctSteptime("1000"));
        assertTrue(correctSteptime("1999"));
        assertFalse(correctSteptime("0"));
        assertFalse(correctSteptime("-19"));
        assertFalse(correctSteptime("asd"));
        assertFalse(correctSteptime("2000"));
    }

    @Test
    public void correctIterationsWorksProperly() {
        assertTrue(correctIterations("1"));
        assertTrue(correctIterations("9"));
        assertTrue(correctIterations("10"));
        assertTrue(correctIterations("99"));
        assertTrue(correctIterations("100"));
        assertTrue(correctIterations("999"));
        assertFalse(correctIterations("0"));
        assertFalse(correctIterations("-4"));
        assertFalse(correctIterations("asd"));
        assertFalse(correctIterations("1000"));
        assertFalse(correctIterations("5391"));
    }

    @Test
    public void listToStrinWorksProperly() {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(0);
        list1.add(5);
        assertEquals("0,5", listToString(list1));
        list1.add(2);
        list1.add(73);
        assertEquals("0,5,2,73", listToString(list1));
    }

}
