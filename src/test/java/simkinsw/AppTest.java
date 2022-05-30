package simkinsw;


import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class AppTest 
{

   /**
    * All the examples from the Evive Engineering Test write-up
    */
    @Test
    public void testProcessOrder()
    {
        App.initializeMeals();

        String input = "Breakfast 1,2,3";
        String result = App.processOrder(input);
        assertEquals("Eggs, Toast, Coffee", result);

        input = "Breakfast 2,3,1";
        result = App.processOrder(input);
        assertEquals("Eggs, Toast, Coffee", result);

        input = "Breakfast 1,2,3,3,3";
        result = App.processOrder(input);
        assertEquals("Eggs, Toast, Coffee(3)", result);

        input = "Breakfast 1";
        result = App.processOrder(input);
        assertEquals("Unable to process: Side is missing", result);

        input = "Lunch 1,2,3";
        result = App.processOrder(input);
        assertEquals("Sandwich, Chips, Soda", result);

        input = "Lunch 1,2";
        result = App.processOrder(input);
        assertEquals("Sandwich, Chips, Water", result);

        input = "Lunch 1,1,2, 3";
        result = App.processOrder(input);
        assertEquals("Unable to process: Sandwich cannot be ordered more than once", result);

        input = "Lunch 1,2,2";
        result = App.processOrder(input);
        assertEquals("Sandwich, Chips(2), Water", result);

        input = "Lunch";
        result = App.processOrder(input);
        assertEquals("Unable to process: Main is missing, Side is missing", result);

        input = "Dinner 1,2,3,4";
        result = App.processOrder(input);
        assertEquals("Steak, Potatoes, Wine, Water, Cake", result);

        input = "Dinner 1,2,3";
        result = App.processOrder(input);
        assertEquals("Unable to process: Dessert is missing", result);
    }


}
