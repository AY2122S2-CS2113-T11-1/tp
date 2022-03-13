package seedu.duke;

public abstract class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    public abstract void execute() throws WrongCommandException;

    public boolean isExit() {
        return this.isExit;
    }
}
