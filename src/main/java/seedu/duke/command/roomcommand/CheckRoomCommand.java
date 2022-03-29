package seedu.duke.command.roomcommand;

import seedu.duke.AssignmentMap;
import seedu.duke.HotelLiteManagerException;
import seedu.duke.InvalidRoomNumberException;
import seedu.duke.ListContainer;
import seedu.duke.Room;
import seedu.duke.RoomList;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.command.RoomHelper;

/**
 * Class that implements execution behavior to list room with corresponding
 * room number.
 * The information includes its type, room id, level and vacancy status.
 */
public class CheckRoomCommand extends Command {
    private int roomId;

    /**
     * Extracts the room level from user input.
     *
     * @param commandStringWithoutCommand contains the information of room number.
     */
    public CheckRoomCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (!RoomHelper.isValidIntNumber(commandStringWithoutCommand.trim())) {
            throw new InvalidRoomNumberException();
        }
        roomId = Integer.parseInt(commandStringWithoutCommand.trim());
    }


    /**
     * Override of execute command in Command class.
     * Print out the room information with corresponding room number
     * including information of:
     * type, room number, level, status and assigned housekeeper.
     *
     * @param listContainer The object containing the necessary data structure.
     * @param ui            The object that deals with user interface for the program.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws InvalidRoomNumberException {
        RoomList roomList = listContainer.getRoomList();
        AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        for (Room room : roomList.getRoomList()) {
            if (room.getRoomId() == roomId) {
                ui.printTableHeader();
                System.out.println(room + "\t\t\t"
                        + assignmentMap.getHouseKeeperNameByRoom(room.getRoomId()));
                return;
            }
        }
        throw new InvalidRoomNumberException();
    }
}
