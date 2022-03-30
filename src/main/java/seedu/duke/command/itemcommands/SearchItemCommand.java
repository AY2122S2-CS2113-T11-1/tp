package seedu.duke.command.itemcommands;

import seedu.duke.command.Command;
import seedu.duke.HotelLiteManagerException;
import seedu.duke.EmptyKeywordException;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.ItemList;

/**
 * Represents a command to search for items within the item list that matches a specific keyword. An SearchItemCommand
 * object consists of the keyword used to search for items within the item list.
 */
public class SearchItemCommand extends Command {
    private String keyword;

    /**
     * Takes in the user input and checks if the formatting of the search item command within the user input is
     * valid.
     * Takes the user input and creates a SearchItemCommand object using it.
     *
     * @param userInput The user's input.
     * @throws HotelLiteManagerException if the keyword is empty.
     */
    public SearchItemCommand(String userInput) throws HotelLiteManagerException {
        String keyword = userInput.trim();
        if (keyword.isEmpty()) {
            throw new EmptyKeywordException();
        }
        setKeyword(keyword);
    }

    /**
     * Searches the item list for items whose name contains the keyword specified by the user.
     * Prints out all the items whose name contains the keyword specified by the user.
     * If no items are found, a message informing the user that no items that contains the keyword is found would be
     * displayed.
     *
     * @param listContainer The object containing the data structure necessary to search for items within the item
     *                      list.
     *                      In this case, we require access to the ItemList object which is within listContainer.
     * @throws HotelLiteManagerException if the keyword is empty.
     */

    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        ItemList listOfItems = listContainer.getItemList();
        String keyword = getKeyword();
        ItemList listOfMatchingItems = listOfItems.findItemsInList(keyword);
        int numberOfItemsFound = listOfMatchingItems.getSize();
        if (numberOfItemsFound == 0) {
            ui.printNoItemsFoundInListAcknowledgementMessage();
        } else {
            ui.printItemList(listOfMatchingItems);
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
