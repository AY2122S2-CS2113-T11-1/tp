package seedu.duke.command.eventcommands;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidAvailabilityException;
import seedu.duke.command.Command;

import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.eventLists.EventList;
import seedu.duke.eventLists.Event;
import seedu.duke.exceptions.InvalidRoomNumberException;
import seedu.duke.exceptions.InvalidHousekeeperProfileException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewEventsCommand extends Command {
    private static Logger logger = Logger.getLogger("log: View events present in the list of events.");

    public ViewEventsCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (!commandStringWithoutCommand.isEmpty()) {
            throw new InvalidAvailabilityException();
        }
        logger.log(Level.INFO, "View events command parsed");
    }


    /**
     * Get the Name of the housekeeper and verify that housekeeper is in records. If in records, add
     * his/her availability into housekeeper list.
     *
     * @param ui The user interface for this execution method.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui)
            throws InvalidRoomNumberException, InvalidHousekeeperProfileException, IOException {
        final EventList eventList = listContainer.getEventList();
        ui.printAllEvents(eventList.getEventList());
        logger.log(Level.INFO, "log: all events displayed");
    }

}