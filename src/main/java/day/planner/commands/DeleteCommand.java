package day.planner.commands;

import day.planner.data.DataStorage;
import day.planner.events.Event;
import day.planner.events.EventDoesNotExist;
import day.planner.events.EventSelector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class DeleteCommand extends Command {


    public DeleteCommand(DataStorage storage, EventSelector eventSelector) {
        super(storage, eventSelector);
    }

    @Override
    public void execute() throws IOException, EventDoesNotExist {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter date of the event you want to delete:");
        String eventDate = reader.readLine();
        deleteEvent(eventDate);
    }

    public void deleteEvent(String date) throws EventDoesNotExist {
        Event event = this.getEventSelector().getEventByHourAndDay(date);

        Map<String, Event> events = this.getStorage().getAllEvents();

        events.remove(event.getDate());

        this.getStorage().refreshDB();

    }
}
