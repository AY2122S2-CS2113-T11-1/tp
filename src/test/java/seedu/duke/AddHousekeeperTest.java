package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddHousekeeperTest {

    @Test
    public void commandParser_addCommandNameAge_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("Add Housekeeper Susan ~ 23");
        AddHousekeeperCommand addHousekeeperCommand = (AddHousekeeperCommand) command;
        assertEquals("Susan", addHousekeeperCommand.getHousekeeper().getName());
        assertEquals(23, addHousekeeperCommand.getHousekeeper().getAge());
    }

    @Test
    public void commandParser_addCommandInvalidAge_exceptionThrown() {
        assertThrows(InvalidAgeException.class, () ->
                new CommandParser().parse("Add Housekeeper Susan ~ fifty"));
    }

    @Test
    public void commandParser_addCommandEmptyDescription_exceptionThrown() {
        assertThrows(InvalidHousekeeperProfile.class, () ->
                new CommandParser().parse("Add Housekeeper ~ "));
    }

    @Test
    public void commandParser_addCommandUnderage_exceptionThrown() {
        assertThrows(UnderAgeException.class, () -> new CommandParser().parse("Add Housekeeper Jane ~ 12"));
    }

    @Test
    public void commandParser_addCommandOverage_exceptionThrown() {
        assertThrows(OverAgeException.class, () -> new CommandParser().parse("Add Housekeeper Sally ~ 81"));
    }
}
