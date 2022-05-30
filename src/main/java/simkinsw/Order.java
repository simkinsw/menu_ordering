package simkinsw;

/**
 * Represents a single order by storing the name of the meal and an array
 * with counts for each menu item.
 */
public class Order {

    private static final int MAX_ID = 3;

    private String mealName;
    private int[] itemCounts;

    /**
     * Constructs an order object by parsing user input and separating the name
     * of the meal and the number of each item ordered.
     * @param input - String provided by user representing a single order
     *        Should be a meal name and a comma-separated list of item numbers 
     */
    public Order(String input) {
        input = input.strip();
        int spaceLoc = input.indexOf(" ");

        String[] stringIDs;
        
        //Handles inputs with no items ordered
        if (spaceLoc != -1) {
            this.mealName = input.substring(0, spaceLoc);
            stringIDs = input.substring(spaceLoc + 1).split(",");
        }
        else {
            this.mealName = input;
            stringIDs = new String[0];
        }

        if(!(mealName.equals("Breakfast") || mealName.equals("Lunch") || mealName.equals("Dinner"))) {
            throw new IllegalArgumentException(mealName + " is not a valid meal");
        }
        
        itemCounts = new int[MAX_ID + 1];
        for (String id : stringIDs) {
            int idNum;
            try {
                //Subtract 1 because the menu is one-indexed
                idNum = Integer.parseInt(id.trim()) - 1;
            } catch (Exception e) {
                throw new NumberFormatException("Invalid Input -- Not a Number");
            }
            if (idNum < 0 || idNum > MAX_ID) {
                throw new ArrayIndexOutOfBoundsException(idNum + " is not a valid menu item");
            }
            itemCounts[idNum]++;
        }   
    }


    public int[] getItemCounts() {
        return itemCounts;
    }


    public String getMealName() {
        return mealName;
    }


}
