package simkinsw;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderTest {
    

    /**
     * Unit Test for input parsing which happens in the Order constructor
     */
    @Test
    public void testOrder() {

        //Basic Functionality
        String input = "Breakfast 1,2,3";
        Order o = new Order(input);
        int[] expected = new int[]{1, 1, 1, 0};
        assertEquals(o.getMealName(), "Breakfast");
        assertArrayEquals(o.getItemCounts(), expected);

        //Multiple meals and missing side
        input = "Lunch 1,1,3";
        o = new Order(input);
        expected = new int[]{2, 0, 1, 0};
        assertEquals(o.getMealName(), "Lunch");
        assertArrayEquals(o.getItemCounts(), expected);

        //Dinner and Dessert
        input = "Dinner 2,3,3,3,4";
        o = new Order(input);
        expected = new int[]{0, 1, 3, 1};
        assertEquals(o.getMealName(), "Dinner");
        assertArrayEquals(o.getItemCounts(), expected);

        //No items
        input = "Lunch";
        o = new Order(input);
        expected = new int[]{0, 0, 0, 0};
        assertEquals(o.getMealName(), "Lunch");
        assertArrayEquals(o.getItemCounts(), expected);
    }
}
