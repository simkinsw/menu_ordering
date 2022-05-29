package simkinsw;

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


    public String getErrorMessage(Order order) {
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
