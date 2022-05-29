package simkinsw;

public class Order {
    private static final int MAX_ID = 3;

    private String mealName;
    private int[] itemCounts;

    public Order(String input) {
        int spaceLoc = input.indexOf(" ");
        this.mealName = input.substring(0, spaceLoc);

        //Handling invalid input?
        String[] stringIDs = input.substring(spaceLoc + 1).split(",");

        //surely this can be fancier
        itemCounts = new int[MAX_ID + 1];
        for (String id : stringIDs) {
            int idNum = Integer.parseInt(id.trim()) - 1;
            if (idNum < 0 || idNum > MAX_ID) {
                //throw exception
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
