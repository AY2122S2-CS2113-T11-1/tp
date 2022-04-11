package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.duke.command.Command;
import seedu.duke.command.assigncommand.AssignHousekeeperCommand;
import seedu.duke.command.housekeepercommands.AddHousekeeperCommand;
import seedu.duke.command.roomcommand.CheckInCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.roomlists.Room;
import seedu.duke.roomlists.RoomList;

import java.io.IOException;


public class AssignHousekeeperTest {
    private ListContainer listContainer = new ListContainer();
    private Ui ui = new Ui();

    public AssignHousekeeperTest() throws HotelLiteManagerException, IOException {
    }

    @BeforeEach
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        Housekeeper housekeeperJame = new Housekeeper("James", 22);
        housekeeperList.addHousekeeperInList(housekeeperJame);
        Housekeeper housekeeperSally = new Housekeeper("Sally", 30);
        housekeeperList.addHousekeeperInList(housekeeperSally);
        RoomList inputRoomList = listContainer.getRoomList();
        Room expectedRoom = null;
        for (Room room : inputRoomList.getRoomList()) {
            if (room.getRoomId() == 301) {
                expectedRoom = room;
            }
        }
    }

    @Test
    public void commandParser_addCommandNameAvailability_success() throws Exception {;
        CommandParser parser = new CommandParser();

        Command command = parser.parse("assign Susan / 301");
        AssignHousekeeperCommand assignHousekeeperCommand = (AssignHousekeeperCommand) command;

        assertEquals("301", assignHousekeeperCommand.getroomID());
        assertEquals("susan", assignHousekeeperCommand.getName());
    }
}
