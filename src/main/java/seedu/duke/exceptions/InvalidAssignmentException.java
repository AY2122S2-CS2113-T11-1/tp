package seedu.duke.exceptions;

public class InvalidAssignmentException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Please add assignment using the format: assign NAME / ROOMID";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}