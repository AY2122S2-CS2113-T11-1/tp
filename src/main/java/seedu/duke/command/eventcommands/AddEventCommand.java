package seedu.duke.command.eventcommands;

import seedu.duke.HotelLiteManagerException;
import seedu.duke.InvalidAvailabilityException;
import seedu.duke.command.Command;

import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.EventList;
import seedu.duke.InvalidRoomNumberException;
import seedu.duke.InvalidHousekeeperProfile;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddEventCommand extends Command {
    private String description;
    private String at;
    private static final String EVENT_INDICATE = "!!";
    private static final int ONLY_ONE_FIELD_ENTERED = 1;
    private static Logger logger = Logger.getLogger("Add Event");

    public AddEventCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
            throw new InvalidAvailabilityException();
        }
        String[] input = extractInput(commandStringWithoutCommand);
        String description = input[0].trim();
        if (input.length == ONLY_ONE_FIELD_ENTERED) {
            throw new InvalidAvailabilityException();
        }
        String id = input[1].trim();
        if (id.isEmpty()) {
            throw new InvalidAvailabilityException();
        }
        setDescription(description);
        setAt(at);
        logger.log(Level.INFO, "Event command parsed");
    }

    /**
     * Method used to extract the name and availability of the housekeeper.
     *
     * @param commandStringWithoutCommand Input entered by the user.
     * @return Input consisting of housekeeper name and availability.
     * @throws HotelLiteManagerException When description of availability is invalid.
     */
    private String[] extractInput(String commandStringWithoutCommand) throws HotelLiteManagerException {
        boolean isSymbolIncorrect = !commandStringWithoutCommand.contains(EVENT_INDICATE);
        if (isSymbolIncorrect) {
            throw new InvalidAvailabilityException();
        }
        String[] input = commandStringWithoutCommand.split(EVENT_INDICATE);
        return input;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAt() {
        return this.at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    /**
     * Get the Name of the housekeeper and verify that housekeeper is in records. If in records, add
     * his/her availability into housekeeper list.
     *
     * @param ui The user interface for this execution method.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui)
            throws InvalidRoomNumberException, InvalidHousekeeperProfile, IOException {

        final EventList eventList = listContainer.getEventList();
        String description = getDescription();
        assert !description.isEmpty() : "description should not be empty";
        String at = getAt();
        assert !at.isEmpty() : "at should not be empty";

        eventList.add(description, at);
        eventList.save();
        ui.printMessage("Added event: " + description + " at " + at + ".");
        logger.log(Level.INFO, "end of adding event.");
    }

}
