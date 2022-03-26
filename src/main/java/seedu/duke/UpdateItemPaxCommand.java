package seedu.duke;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Represents a command to update the pax of an item within the item list. An UpdateItemPaxCommand object consists of
 * the name of the item to update and the new pax value.
 */
public class UpdateItemPaxCommand extends Command {
    private Item item;
    private static final String ITEM_NAME_INDICATOR = "/Name:";
    private static final int ITEM_NAME_INDICATOR_LENGTH = 6;
    private static final String ITEM_PAX_INDICATOR = "/New Pax:";
    private static final int ITEM_PAX_INDICATOR_LENGTH = 9;
    private static Logger itemLogger = Logger.getLogger("itemLogger");

    /**
     * Takes in the user input and checks if the formatting of the Update Item Pax Command within the user input is
     * valid.
     * Extracts out the item name and item pax from the user input and creates an UpdateItemPaxCommand object.
     *
     * @param userInput The user's input.
     * @throws HotelLiteManagerException if the formatting of the update item pax command is invalid, the item pax is
     *                                   empty or invalid, the item name is empty, or the item name and pax are both
     *                                   empty.
     */
    public UpdateItemPaxCommand(String userInput) throws HotelLiteManagerException {
        boolean isValidUpdateItemCommand = userInput.contains(ITEM_NAME_INDICATOR)
                && userInput.contains(ITEM_PAX_INDICATOR);
        if (!isValidUpdateItemCommand) {
            itemLogger.log(Level.WARNING, "Invalid formatting for UpdateItemPaxCommand detected. Exception "
                    + "thrown");
            throw new InvalidCommandException();
        }
        int itemPax = extractItemPax(userInput);
        String itemName = extractItemName(userInput);
        Item item = new Item(itemName, itemPax);
        setItem(item);
    }

    /**
     * Returns the item name extracted from the user input.
     *
     * @param userInput The user's input.
     * @return The item name within the user input.
     * @throws HotelLiteManagerException if item name is empty.
     */
    private String extractItemName(String userInput) throws HotelLiteManagerException {
        String itemName;
        int itemNameStartingPosition = userInput.indexOf(ITEM_NAME_INDICATOR) + ITEM_NAME_INDICATOR_LENGTH;
        int itemPaxIndicatorPosition = userInput.indexOf(ITEM_PAX_INDICATOR);
        itemName = userInput.substring(itemNameStartingPosition, itemPaxIndicatorPosition);
        itemName = itemName.trim();
        if (itemName.isEmpty()) {
            itemLogger.log(Level.WARNING, "Detected an empty item name for UpdateItemPaxCommand. Exception "
                    + "thrown.");
            throw new EmptyItemNameException();
        }
        return itemName;
    }

    /**
     * Returns the item pax extracted from the user input.
     *
     * @param userInput The user's input.
     * @return The item pax within the user input.
     * @throws HotelLiteManagerException if item pax is empty or invalid.
     */
    private int extractItemPax(String userInput) throws HotelLiteManagerException {
        int itemPax;
        String itemPaxStringVersion;
        int stringEndingPosition = userInput.length();
        int itemPaxStartingPosition = userInput.indexOf(ITEM_PAX_INDICATOR) + ITEM_PAX_INDICATOR_LENGTH;
        if (itemPaxStartingPosition == stringEndingPosition) {
            itemLogger.log(Level.WARNING, "Detected an empty item pax for UpdateItemPaxCommand. Exception "
                    + "thrown.");
            throw new EmptyItemPaxException();
        }
        itemPaxStringVersion = userInput.substring(itemPaxStartingPosition);
        try {
            itemPax = Integer.parseInt(itemPaxStringVersion);
        } catch (NumberFormatException e) {
            itemLogger.log(Level.WARNING, "Detected an invalid item pax for UpdateItemPaxCommand. Exception "
                    + "thrown");
            throw new InvalidItemPaxException();
        }
        if (itemPax < 0) {
            itemLogger.log(Level.WARNING, "Detected an invalid item pax for UpdateItemPaxCommand. Exception "
                    + "thrown");
            throw new InvalidItemPaxException();
        }
        return itemPax;
    }

    /**
     * Updates the pax of the item within the item list using the item name and new pax found in the instance variable
     * named item within the UpdateItemPaxCommand object.
     *
     * @param housekeeperList  The list of housekeeper recorded. N/A for this class, but
     *                         must be included for the execution override.
     * @param satisfactionList The given list of Satisfaction objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param assignmentMap    The assignments of the various housekeepers.
     * @param roomList         The given list of Room objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param listOfItems      The list of items within the inventory.
     * @param ui               The object that deals with user interface for the program.
     * @throws HotelLiteManagerException if the item name within the item object does not exist in the item list.
     */
    @Override
    public void execute(HousekeeperList housekeeperList, HousekeeperPerformanceList housekeeperPerformanceList,
                        SatisfactionList satisfactionList,
                        AssignmentMap assignmentMap, RoomList roomList,
                        ItemList listOfItems, Ui ui) throws HotelLiteManagerException {
        assert (item != null) : "Assertion Failed! There is no item within the UpdateItemPaxCommand object.";
        listOfItems.updateItemPaxInList(item);
        ui.printUpdateItemPaxAcknowledgementMessage(item);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
