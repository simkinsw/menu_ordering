package evive;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;


public class App 
{

    public static Map<String, Meal> mealObjects;

    public static void main( String[] args )
    {
        createMealRules();

        processOrder("Breakfast 1");
    }


    public static void processOrder(String input) {
        int spaceLoc = input.indexOf(" ");
        String mealName = input.substring(0, spaceLoc);

        //Handling invalid input?
        String[] stringIDs = input.substring(spaceLoc + 1).split(",");

        //surely this can be fancier
        List<Integer> itemIDs = new ArrayList<Integer>();
        for (String id : stringIDs) {
            itemIDs.add(Integer.parseInt(id));
        }

        Order order = new Order(mealName, itemIDs);
        Meal meal = mealObjects.get(mealName);
        System.out.println(meal.generateOutput(order));
    }


    public static void createMealRules() {
        Meal breakfast = new Meal();
        Meal lunch = new Meal();
        Meal dinner = new Meal();

        mealObjects = ImmutableMap.of("Breakfast", breakfast, "Lunch", lunch, "Dinner", dinner);

        //TODO: This needs to say the name of the meal for errorHigh
        Rule exactlyOneMainDish = new Rule(1, 1, 1, "Meal is missing", "Meal cannot be ordered more than once");
    
        //TODO: This needs to say the name of the side for errorHigh
        Rule exactlyOneSide = new Rule(2, 1, 1, "Side is missing", "Side cannot be ordered more than once");

        Rule unlimitedCoffee = new Rule(3, 0, Integer.MAX_VALUE, "Unreachable errorLow", "Unreachable errorHigh");
        Rule noDessert = new Rule(4, 0, 0, "Unreachable errorLow", "Dessert cannot be ordered with breakfast");

        breakfast.addRule(exactlyOneMainDish);
        breakfast.addRule(exactlyOneSide);
        breakfast.addRule(unlimitedCoffee);
        breakfast.addRule(noDessert);
    }
}
