package seedu.duke;

import seedu.duke.storage.EventListFileManager;
import seedu.duke.storage.RoomFileManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a data structure containing multiple Event objects.
 * The constructor will initialize all rooms with status vacant
 */
public class EventList {
    private ArrayList<Event> eventList;

    public EventList(ArrayList<Event> eventList) {  // dummy data for initialization
        this.eventList = eventList;
    }

    public ArrayList<Event> getEventList() {
        return this.eventList;
    }

    public void add(String description, String atString) {
        try {
            LocalDate at = LocalDate.parse(atString);
            Event event = new Event(description, at);
            eventList.add(event);
        } catch (Exception e) {
            System.out.println("Please add date in yyyy-mm-dd format.");
        }
    }

    public void save() throws IOException {
        EventListFileManager eventFileManager = new EventListFileManager();
        eventFileManager.save(eventList);
    }
}