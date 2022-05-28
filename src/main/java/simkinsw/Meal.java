package simkinsw;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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


    public String generateOutput(Order o) {
        StringJoiner sj = new StringJoiner(", ", "Unable to process: ", "");
        boolean validOrder = true;

        for (Rule rule : rules) {
            if (!rule.passesRule(o)) {
                String error = rule.getError(o);
                error = error.replace("***", menu[rule.getIndex()]);
                sj.add(error);
                validOrder = false;
            }
        }

        if (!validOrder) {
            return sj.toString();
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
