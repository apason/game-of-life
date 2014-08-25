
import logic.Utilities;
import static logic.Utilities.correctConditionList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
    public void correctFilenameWorksProperly(){
        assertEquals("testfilename.dat", Utilities.correctFilename("testfilename.dat"));
        assertEquals("testfilename.dat", Utilities.correctFilename("testfilename"));
    }
    @Test
    public void correctConditionListWorksProperly(){
        assertTrue(correctConditionList("1,2,3,4"));
        assertFalse(correctConditionList("1,2,3,4,"));
        assertFalse(correctConditionList("100"));
        assertTrue(correctConditionList("10"));
        assertFalse(correctConditionList("1,3,b,5"));
        assertTrue(correctConditionList("5,10,66"));
        
    }
    
}
