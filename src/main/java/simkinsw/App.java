package simkinsw;

import java.util.Map;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution for Evive Engineering Test 
 * System that takes String orders for breakfast, lunch, and dinner and outputs
 * the items they ordered according to a set of rules and a menu
 * 
 * @author Will Simkins 
 * @since 5/29/22
 */

public class App 
{

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private static final String[] BREAKFAST_MENU = {"Eggs", "Toast", "Coffee"};
    private static final String[] LUNCH_MENU = {"Sandwich", "Chips", "Soda"};
    private static final String[] DINNER_MENU = {"Steak", "Potatoes", "Wine", "Cake"};

    //References to each of the 3 mealObjects by String ("Breakfast", "Lunch", or "Dinner")
    public static Map<String, Meal> mealObjects;


    public static void main( String[] args )
    {
        initializeMeals();
        if (args.length < 1) {
            LOGGER.warn("Please provide input, ex. \"Breakfast 1, 2, 3\"");
        }
        else {
            LOGGER.info(processOrder(args[0]));
        }
    }

    /**
     * Processes a single order
     * @param input - String provided by user representing a single order
     *        Should be a meal name and a comma-separated list of item numbers    
     * @return String with the names of the items ordered or an error message
     *         for invalid orders
     */
    public static String processOrder(String input) {
        Order o = new Order(input);
        String mealName = o.getMealName();
        Meal meal = mealObjects.get(mealName);

        return meal.generateOutput((o));
    }

    /**
     * Run once at the beginning to create Breakfast, Lunch, and dinner Meal objects, 
     * add them to the mealObjects map and populate them with Rules
     */
    public static void initializeMeals() {
        Meal breakfast = new Meal(BREAKFAST_MENU);
        Meal lunch = new Meal(LUNCH_MENU);
        Meal dinner = new Meal(DINNER_MENU);

        mealObjects = ImmutableMap.of("Breakfast", breakfast, "Lunch", lunch, "Dinner", dinner);

        Rule exactlyOneMainDish = new Rule(0, 1, 1, "Main is missing", "*** cannot be ordered more than once");
        Rule exactlyOneSide = new Rule(1, 1, 1, "Side is missing", "*** cannot be ordered more than once");
        Rule noDessert = new Rule(3, 0, 0, "Unreachable errorLow", "Dessert cannot be ordered with breakfast");

        breakfast.addRule(exactlyOneMainDish);
        breakfast.addRule(exactlyOneSide);
        breakfast.addRule(noDessert);

        Rule atLeastOneSide = new Rule(1, 1, Integer.MAX_VALUE, "Side is missing", "Unreachable errorHigh");
        Rule atMostOneDrink = new Rule(2, 0, 1, "Unreachable errorLow", "*** cannot be ordered more than once");

        lunch.addRule(exactlyOneMainDish);
        lunch.addRule(atLeastOneSide);
        lunch.addRule(atMostOneDrink);
        lunch.addRule(noDessert);

        Rule exactlyOneDessert = new Rule(3, 1, 1, "Dessert is missing", "*** cannot be ordered more than once");

        dinner.addRule(exactlyOneMainDish);
        dinner.addRule(exactlyOneSide);
        dinner.addRule(atMostOneDrink);
        dinner.addRule(exactlyOneDessert);
    }
}
