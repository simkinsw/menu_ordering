package evive;

import java.util.List;

public class Order {
    private static final int MIN_ID = 1;
    private static final int MAX_ID = 4;

    private String mealName;
    private int[] itemCounts;


    public Order(String mealName, List<Integer> itemIDs) {
        this.mealName = mealName;
        
        itemCounts = new int[MAX_ID + 1];
        for (Integer id : itemIDs) {
            if (id < MIN_ID || id > MAX_ID) {
                //throw some exception here 
            }
            itemCounts[id]++;
        }
    }

    public int[] getItemCounts() {
        return itemCounts;
    }

    public String getMealName() {
        return mealName;
    }


}
