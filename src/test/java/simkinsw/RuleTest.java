package simkinsw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RuleTest {
    
    /** 
     * Testing the passesRule and getError methods of the Rule class
     */


    //Testing functionality for an Order passing a rule
    @Test
    public void testPassesRuleEqual() {
        Rule exactlyOneMainDish = new Rule(0, 1, 1, "Main is missing", "*** cannot be ordered more than once");
        Order o = new Order("Breakfast 1,2,3");
        assertTrue(exactlyOneMainDish.passesRule(o));
    }

    //Testing passesRule and getError for an order where too many of an item is ordered
    @Test
    public void testPassesRuleOver() {
        Rule atMostOneDrink = new Rule(2, 0, 1, "Unreachable errorLow", "*** cannot be ordered more than once");
        Order o = new Order("Breakfast 1,2,3,3");
        assertFalse(atMostOneDrink.passesRule(o));
        String error = atMostOneDrink.getError(o);
        assertEquals("*** cannot be ordered more than once", error);
    }

    //Testing passesRule and getError for an order where too few of an item is ordered
    @Test
    public void testPassesRuleUnder() {
        Rule atLeastOneSide = new Rule(1, 1, Integer.MAX_VALUE, "Side is missing", "Unreachable errorHigh");
        Order o = new Order("Lunch 1,3");
        assertFalse(atLeastOneSide.passesRule(o));
        String error = atLeastOneSide.getError(o);
        assertEquals("Side is missing", error);
    }

}
