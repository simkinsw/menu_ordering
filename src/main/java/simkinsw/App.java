package simkinsw;

import java.util.Map;
import com.google.common.collect.ImmutableMap;


public class App 
{

    private static final String[] BREAKFAST_MENU = {"Eggs", "Toast", "Coffee"};
    private static final String[] LUNCH_MENU = {"Sandwich", "Chips", "Soda"};
    private static final String[] DINNER_MENU = {"Steak", "Potatoes", "Wine", "Cake"};

    private static Meal breakfast;
    private static Meal lunch;
    private static Meal dinner;

    public static Map<String, Meal> mealObjects;

    public static void main( String[] args )
    {
        initializeMeals();
        System.out.println(processOrder("Lunch 1,1,2, 3"));
        
    }


    public static String processOrder(String input) {
        Order o = new Order(input);
        String mealName = o.getMealName();
        Meal meal = mealObjects.get(mealName);

        return meal.generateOutput((o));
    }


    public static void initializeMeals() {
        breakfast = new Meal(BREAKFAST_MENU);
        lunch = new Meal(LUNCH_MENU);
        dinner = new Meal(DINNER_MENU);

        mealObjects = ImmutableMap.of("Breakfast", breakfast, "Lunch", lunch, "Dinner", dinner);

        Rule exactlyOneMainDish = new Rule(0, 1, 1, "Meal is missing", "*** cannot be ordered more than once");
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
