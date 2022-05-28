package simkinsw;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.google.common.collect.ImmutableMap;

public class Meal {

    private static final String[] BREAKFAST_MENU = {"", "Eggs", "Toast", "Coffee"};
    private static final String[] LUNCH_MENU = {"", "Sandwich", "Chips", "Soda"};
    private static final String[] DINNER_MENU = {"", "Steak", "Potatoes", "Wine", "Cake"};
    private static final Map<String, String[]> itemNames = ImmutableMap.of("Breakfast", BREAKFAST_MENU, 
                                                                "Lunch", LUNCH_MENU, "Dinner", DINNER_MENU);

    private List<Rule> rules;


    public Meal() {
        rules = new ArrayList<Rule>();
    }


    public void addRule(Rule newRule) {
        rules.add(newRule);
    }


    //TODO: Water is Wrong
    public String generateOutput(Order o) {
        String output = "Unable to process: ";
        boolean validOrder = true;

        for (Rule rule : rules) {
            if (!rule.passesRule(o)) {
                output += rule.getError(o) + ",";
                validOrder = false;
            }
        }

        if (!validOrder) {
            return output.substring(0, output.length() - 1);
        }

        output = "";
        String mealName = o.getMealName();
        int[] itemCounts = o.getItemCounts();
        for (int i = 1; i < itemCounts.length; i++) {
            int count = itemCounts[i];
            if (count == 1) {
                output += itemNames.get(mealName)[i] + ", ";
            }
            else if (count > 1) {
                output += itemNames.get(mealName)[i] + "(" + count + "), ";
            }
        }

        return output.substring(0, output.length() - 2);
    }

}
