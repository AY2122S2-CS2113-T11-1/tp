package seedu.duke;

import java.io.IOException;
import java.util.logging.LogManager;

import seedu.duke.command.Command;
import seedu.duke.command.itemcommands.AddItemCommand;
import seedu.duke.command.itemcommands.DeleteItemCommand;
import seedu.duke.command.itemcommands.UpdateItemNameCommand;
import seedu.duke.command.itemcommands.UpdateItemPaxCommand;
import seedu.duke.exceptions.HotelLiteManagerException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private void writeListsToFile(Command command, ListContainer listContainer) throws IOException {
        if (command instanceof AddItemCommand || command instanceof UpdateItemPaxCommand || command instanceof
                UpdateItemNameCommand || command instanceof UpdateItemNameCommand || command instanceof
                DeleteItemCommand) {
            writeItemListsToFile(command, listContainer);
        }
    }


    private void writeItemListsToFile(Command command, ListContainer listContainer) throws IOException {
        if (command instanceof AddItemCommand) {
            AddItemCommand addItemCommand = (AddItemCommand) command;
            addItemCommand.writeItemListToFile(listContainer);
        } else if (command instanceof UpdateItemPaxCommand) {
            UpdateItemPaxCommand updateItemPaxCommand = (UpdateItemPaxCommand) command;
            updateItemPaxCommand.writeItemListToFile(listContainer);
        } else if (command instanceof UpdateItemNameCommand) {
            UpdateItemNameCommand updateItemNameCommand = (UpdateItemNameCommand) command;
            updateItemNameCommand.writeItemListToFile(listContainer);
        } else if (command instanceof DeleteItemCommand) {
            DeleteItemCommand deleteItemNameCommand = (DeleteItemCommand) command;
            deleteItemNameCommand.writeItemListToFile(listContainer);
        }
    }

    private void run() {
        Ui ui = new Ui();
        ui.printGreeting();
        CommandParser commandParser = new CommandParser();
        ListContainer listContainer = null;
        try {
            listContainer = new ListContainer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HotelLiteManagerException e) {
            ui.printErrorMessage(e);
        }

        boolean shouldExitProgram = false;
        String userInput;
        while (!shouldExitProgram) {
            Command command = null;
            try {
                userInput = ui.readUserInput();
                command = commandParser.parse(userInput);
                command.execute(listContainer, ui);
                writeListsToFile(command, listContainer);
                shouldExitProgram = command.isExit();
            } catch (HotelLiteManagerException e) {
                ui.printErrorMessage(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        new Duke().run();
    }
}
