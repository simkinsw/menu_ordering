package simkinsw;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderTest {
    

    /**
     * Unit Tests for input parsing which happens in the Order constructor
     */

    //Test Basic Functionality
    @Test
    public void testOrderStandard() { 
        String input = "Breakfast 1,2,3";
        Order o = new Order(input);
        int[] expected = new int[]{1, 1, 1, 0};
        assertEquals(o.getMealName(), "Breakfast");
        assertArrayEquals(o.getItemCounts(), expected);
    }


    //Test Multiple meals and missing side
    @Test
    public void testOrderTwoMealsNoSide() {
        String input = "Lunch 1,1,3";
        Order o = new Order(input);
        int[] expected = new int[]{2, 0, 1, 0};
        assertEquals(o.getMealName(), "Lunch");
        assertArrayEquals(o.getItemCounts(), expected);
    }

    //Tests Dinner and Dessert
    @Test
    public void testOrderDinnerDessert() {
        String input = "Dinner 2,3,3,3,4";
        Order o = new Order(input);
        int[] expected = new int[]{0, 1, 3, 1};
        assertEquals(o.getMealName(), "Dinner");
        assertArrayEquals(o.getItemCounts(), expected);
    }


    //Test order with no items
    @Test
    public void testOrderNoItems() {
        String input = "Lunch";
        Order o = new Order(input);
        int[] expected = new int[]{0, 0, 0, 0};
        assertEquals(o.getMealName(), "Lunch");
        assertArrayEquals(o.getItemCounts(), expected);
    }
}
