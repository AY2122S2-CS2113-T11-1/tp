package seedu.duke;

import java.util.Scanner;

/**
 * Represents the User Interface component of the program.
 * The Ui object handles all interactions with the user such as printing the outputs to the user
 * as well as reading in the user inputs .
 */
public class Ui {
    private Scanner in;
    private static final int ARRAY_INDEX_OFFSET = 1;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Returns a string containing the user input.
     *
     * @return User input
     */
    public String readUserInput() {
        String userInput = in.nextLine();
        return userInput;
    }


    public void printErrorMessage(HotelLiteManagerException exception) {
        System.out.println(exception.getErrorMessage());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printHousekeeperNoted(Housekeeper housekeeper) {
        System.out.println("Noted!");
        System.out.println(housekeeper);

    }

    /**
     * Prints out an acknowledgement message to inform the user that the item has been added to the item list as well
     * as the number of items within the item list.
     *
     * @param listOfItems The item list containing all the items in the inventory.
     */
    public void printAddItemAcknowledgementMessage(ItemList listOfItems) {
        System.out.println("The item and its pax has been added to the list of items in the inventory.");
        assert (listOfItems.getSize() > 0) : "Assertion Failed! Number of items in the item list is 0 after an item "
                + "was added to it.";
        System.out.printf("There are currently %d items within the inventory.\n", listOfItems.getSize());
    }

    /**
     * Prints out the item name ,pax as well as index for each item that is found within the item list.
     *
     * @param listOfItems The item list containing all the items in the inventory.
     */
    public void printItemList(ItemList listOfItems) {
        String itemName;
        int itemPax;
        Item currentItem;
        int currentListIndex;
        for (int i = 0; i < listOfItems.getSize(); i++) {
            currentItem = listOfItems.getItem(i);
            itemName = currentItem.getName();
            itemPax = currentItem.getPax();
            assert (itemPax >= 0) : "Assertion Failed! Pax of an item within the listOfItems is less than 0 !";
            assert (!itemName.isEmpty()) : "Assertion Failed! The name of the item within the listOfItems is empty !";
            currentListIndex = i + ARRAY_INDEX_OFFSET;
            System.out.printf("%d. Item Name: %s Item Pax: %d\n", currentListIndex, itemName, itemPax);
        }
    }

    public void printHousekeeperList(HousekeeperList housekeeperList) {
        printMessage("======== Housekeeper List ========");
        for (int i = 0; i < housekeeperList.getTotalHousekeeper(); i++) {
            System.out.println((i + 1) + ". " + housekeeperList.getHousekeeper(i));
        }
        System.out.println("======== End of the list ========");
    }

    /**
     * Prints an acknowledgement message informing the user that the pax of the item was successfully updated.
     *
     * @param updatedItem The item within the inventory whose pax has been updated by the user.
     */
    public void printUpdateItemPaxAcknowledgementMessage(Item updatedItem) {
        String updatedItemName = updatedItem.getName();
        int updatedItemNewPax = updatedItem.getPax();
        assert (!updatedItemName.isEmpty()) : "Assertion Failed! Updated item has an empty item name.";
        assert (updatedItemNewPax >= 0) : "Assertion Failed! Updated item has a pax that is less than 0.";
        System.out.printf("The pax of %s has been updated to %d.\n", updatedItemName, updatedItemNewPax);
    }

    public void printAddSatisfactionAcknowledgementMessage(SatisfactionList satisfactionList,
                                                           Satisfaction recentSatisfaction) {
        System.out.println("The Satisfaction instance " + recentSatisfaction.getCustomerName() + ": "
                + recentSatisfaction.getSatisfactionValue() + " has been added to the list of Satisfactions.");
        System.out.printf("There are currently %d items within the inventory.\n", satisfactionList.getSize());
    }

    public void printAddHousekeeperPerformanceAcknowledgementMessage(HousekeeperPerformanceList housekeeperPerformanceList,
                                                                     HousekeeperPerformance housekeeperPerformance) {
        System.out.println("The HousekeeperPerformance instance " + housekeeperPerformance.getName() + ": "
            + housekeeperPerformance.getRating() + " has been added to the list of housekeeper performances.");
        System.out.printf("There are currently %d items within the inventory.\n", housekeeperPerformanceList.getSize());

    }
}

