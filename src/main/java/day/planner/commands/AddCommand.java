package day.planner.commands;

import day.planner.data.DataStorage;
import day.planner.data.DataValidator;
import day.planner.events.Event;
import day.planner.events.EventSelector;
import day.planner.events.InvalidEventMarker;
import day.planner.events.InvalidEventType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddCommand extends Command {


    public AddCommand(DataStorage storage, EventSelector eventSelector) {
        super(storage, eventSelector);
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter event type : task or date:");
        String type = reader.readLine();
        System.out.println("Enter event marker:" + System.lineSeparator()
                + "Public" + System.lineSeparator()
                + "Confidential" + System.lineSeparator()
                + "Private");

        String marker = reader.readLine();
        System.out.println("Enter hour and day for the event in format 12M-12D-12H");
        String date = reader.readLine();

        while (!DataValidator.validateDate(date)) {
            date = reader.readLine();
        }

        while (super.getStorage().getAllEvents().containsKey(date)) {
            System.out.println("Event already exists." + System.lineSeparator() + "Please enter another date.");
            date = reader.readLine();
        }
        System.out.println("Enter event description:");
        String description = reader.readLine();
        Event event = null;
        try {
            event = new Event(type, date, marker, description);
        } catch (InvalidEventType | InvalidEventMarker e) {
            System.out.println(e.getMessage());
        }
        addEvent(event);
    }

    private void addEvent(Event event) {
        this.getStorage().writeEvent(event);

    }
}
