package simkinsw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RuleTest {
    
    /** 
     * Testing the passesRule and getError methods of the Rule class
     */
    @Test
    public void testPassesRule() {
        Rule exactlyOneMainDish = new Rule(0, 1, 1, "Main is missing", "*** cannot be ordered more than once");
        Order o = new Order("Breakfast 1,2,3");
        assertTrue(exactlyOneMainDish.passesRule(o));
        o = new Order("Breakfast 2,3");
        assertFalse(exactlyOneMainDish.passesRule(o));
        assertEquals("Main is missing", exactlyOneMainDish.getError(o));
        o = new Order("Breakfast 1,1,2,3");
        assertFalse(exactlyOneMainDish.passesRule(o));
        assertEquals("*** cannot be ordered more than once", exactlyOneMainDish.getError(o));
        
        Rule atLeastOneSide = new Rule(1, 1, Integer.MAX_VALUE, "Side is missing", "Unreachable errorHigh");
        o = new Order("Lunch 1,2,3");
        assertTrue(atLeastOneSide.passesRule(o));
        o = new Order("Lunch 1,2,2,2,2,2,3");
        assertTrue(atLeastOneSide.passesRule(o));
        o = new Order("Lunch 1,3");
        assertFalse(atLeastOneSide.passesRule(o));

        Rule atMostOneDrink = new Rule(2, 0, 1, "Unreachable errorLow", "*** cannot be ordered more than once");
        o = new Order("Lunch 1,2,3");
        assertTrue(atMostOneDrink .passesRule(o));
        o = new Order("Lunch 1,2");
        assertTrue(atMostOneDrink .passesRule(o));
        o = new Order("Lunch 1,2,3,3");
        assertFalse(atMostOneDrink .passesRule(o));
    }
}
