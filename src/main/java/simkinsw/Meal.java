package simkinsw;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Represents a single meal as an array of foods that can be ordered for that meal
 * and a list of rules that each order must follow (e.g. At dinner, dessert must be ordered)
 */
public class Meal {

    private List<Rule> rules;
    private String[] menu;


    public Meal(String[] menu) {
        rules = new ArrayList<Rule>();
        this.menu = menu;
    }


    public void addRule(Rule newRule) {
        rules.add(newRule);
    }

    /**
     * Validates and produces output for a single Order object associated with this meal
     * @param o - A single order made for this meal
     * @return A String which is a list of foods ordered for valid orders or an error 
     *         message for orders which do not follow all rules
     */
    public String generateOutput(Order o) {
        StringJoiner errorMessage = new StringJoiner(", ", "Unable to process: ", "");
        boolean validOrder = true;

        for (Rule rule : rules) {
            if (!rule.passesRule(o)) {
                String error = rule.getError(o);
                error = error.replace("***", menu[rule.getIndex()]);
                errorMessage.add(error);
                validOrder = false;
            }
        }

        if (!validOrder) {
            return errorMessage.toString();
        }
        

        List<String> outputItems = new ArrayList<String>();
        int[] itemCounts = o.getItemCounts();
        for (int i = 0; i < itemCounts.length; i++) {
            int count = itemCounts[i];
            if (count == 1) {
                outputItems.add(menu[i]);
            }
            else if (count > 1) {
                outputItems.add(menu[i] + "(" + count + ")");
            }
        }
        if(o.getMealName().equals("Dinner")) {
            outputItems.add(outputItems.size() - 1, "Water");
        }
        else if(itemCounts[2] == 0) {
            outputItems.add("Water");
        }

        return String.join(", ", outputItems);
    }

}
