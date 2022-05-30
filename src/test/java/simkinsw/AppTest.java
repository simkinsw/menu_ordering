package simkinsw;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class AppTest 
{

    @Before
    public void createMeals() {
        App.initializeMeals();
    }

   /**
    * All the examples from the Evive Engineering Test write-up
    */

    @Test
    public void testExampleInput1() {
        String input = "Breakfast 1,2,3";
        String result = App.processOrder(input);
        assertEquals("Eggs, Toast, Coffee", result);
    }

    @Test
    public void testExampleInput2() {
        String input = "Breakfast 2,3,1";
        String result = App.processOrder(input);
        assertEquals("Eggs, Toast, Coffee", result);
    }

    @Test
    public void testExampleInput3() {
        String input = "Breakfast 1,2,3,3,3";
        String result = App.processOrder(input);
        assertEquals("Eggs, Toast, Coffee(3)", result);
    }

    @Test
    public void testExampleInput4() {
        String input = "Breakfast 1";
        String result = App.processOrder(input);
        assertEquals("Unable to process: Side is missing", result);
    }

    @Test
    public void testExampleInput5() {
        String input = "Lunch 1,2,3";
        String result = App.processOrder(input);
        assertEquals("Sandwich, Chips, Soda", result);
    }

    @Test
    public void testExampleInput6() {
        String input = "Lunch 1,2";
        String result = App.processOrder(input);
        assertEquals("Sandwich, Chips, Water", result);
    }

    @Test
    public void testExampleInput7() {
        String input = "Lunch 1,1,2, 3";
        String result = App.processOrder(input);
        assertEquals("Unable to process: Sandwich cannot be ordered more than once", result);
    }

    @Test
    public void testExampleInput8() {
        String input = "Lunch 1,2,2";
        String result = App.processOrder(input);
        assertEquals("Sandwich, Chips(2), Water", result);
    }

    @Test
    public void testExampleInput9() {
        String input = "Lunch";
        String result = App.processOrder(input);
        assertEquals("Unable to process: Main is missing, Side is missing", result);
    }

    @Test
    public void testExampleInput10() {
        String input = "Dinner 1,2,3,4";
        String result = App.processOrder(input);
        assertEquals("Steak, Potatoes, Wine, Water, Cake", result);
    }

    @Test
    public void testExampleInput11() {
        String input = "Dinner 1,2,3";
        String result = App.processOrder(input);
        assertEquals("Unable to process: Dessert is missing", result);
    }
}
