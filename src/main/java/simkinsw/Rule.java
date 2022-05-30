package simkinsw;

/**
 * A single condition used by the Meal class to determine if each order is valid
 * e.g. "Each order must contain a main and a side"
 *
 * Rules provide minimum and maximum values for a single item ID and specify
 * the error message(s) which should be output if a condition is not met
 * 
 * Could be extended to handle more complex rules
 */
public class Rule {
    
    private int index;
    private int min;
    private int max;
    private String errorLow;
    private String errorHigh;


    public Rule(int index, int min, int max, String errorLow, String errorHigh) {
        this.index = index;
        this.min = min;
        this.max = max;
        this.errorLow = errorLow;
        this.errorHigh = errorHigh;
    }


    public int getIndex() {
        return index;
    }

    /**
     * 
     * @param order - An single Order object to be checked
     * @return The appropriate error message if order does not satisfy this rule
     *         or "No Error" if it does satisfy
     */

    public String getError(Order order) {
        int itemCount = order.getItemCounts()[index];
        if (itemCount < min) {
            return errorLow;
        }
        else if (itemCount > max){
            return errorHigh;
        }
        else {
            return "No Error";
        }
    }


    /**
     * 
     * @param order - A single Order object to be checked
     * @return True if order satisfies both the min and max condition of this rule
     *         or False if it does not satisfy one or both conditions 
     */
    public boolean passesRule (Order order){
        int itemCount = order.getItemCounts()[index];
        if (itemCount >= min && itemCount <= max) {
            return true;
        }
        else {
            return false;
        }
    }

}
