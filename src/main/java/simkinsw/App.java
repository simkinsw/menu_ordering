package simkinsw;

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

        System.out.println(processOrder("Dinner 1,2,3"));
    }


    public static String processOrder(String input) {
        //TODO: Lots to do here to handle invalid inputs. E.g. "Lunch"

        int spaceLoc = input.indexOf(" ");
        String mealName = input.substring(0, spaceLoc);

        //Handling invalid input?
        String[] stringIDs = input.substring(spaceLoc + 1).split(",");

        //surely this can be fancier
        List<Integer> itemIDs = new ArrayList<Integer>();
        for (String id : stringIDs) {
            itemIDs.add(Integer.parseInt(id.trim()));
        }

        Order order = new Order(mealName, itemIDs);
        Meal meal = mealObjects.get(mealName);
        return meal.generateOutput(order);
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
        Rule noDessert = new Rule(4, 0, 0, "Unreachable errorLow", "Dessert cannot be ordered with breakfast");

        breakfast.addRule(exactlyOneMainDish);
        breakfast.addRule(exactlyOneSide);
        breakfast.addRule(noDessert);

        Rule atLeastOneSide = new Rule(2, 1, Integer.MAX_VALUE, "Side is missing", "Unreachable errorHigh");
        //TODO: Say the name of the drink I guess
        Rule atMostOneDrink = new Rule(3, 0, 1, "Unreachable errorLow", "Drink cannot be ordered more than once");

        lunch.addRule(exactlyOneMainDish);
        lunch.addRule(atLeastOneSide);
        lunch.addRule(atMostOneDrink);
        lunch.addRule(noDessert);

        Rule exactlyOneDessert = new Rule(4, 1, 1, "Dessert is missing", "Dessert cannot be ordered more than once");

        dinner.addRule(exactlyOneMainDish);
        dinner.addRule(exactlyOneSide);
        dinner.addRule(atMostOneDrink);
        dinner.addRule(exactlyOneDessert);
    }
}
