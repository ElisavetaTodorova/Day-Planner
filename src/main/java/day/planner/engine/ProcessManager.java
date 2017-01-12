package day.planner.engine;

import day.planner.commands.*;
import day.planner.data.DataStorage;
import day.planner.events.*;
import dnl.utils.text.table.TextTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;


public class ProcessManager {

    private DataStorage storage;
    private EventSelector eventSelector;

    public ProcessManager() {
        this.storage = new DataStorage();
        this.eventSelector = new EventSelector(this.storage);
    }

    public void run() throws IOException, EventDoesNotExist {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter command");
            System.out.println(CommnadMessages.COMMAND_ADD.getValue());
            System.out.println(CommnadMessages.COMMAND_MODIFY.getValue());
            System.out.println(CommnadMessages.COMMAND_DELETE.getValue());
            System.out.println(CommnadMessages.COMMAND_GET_EVENTS_BY.getValue());
            System.out.println(CommnadMessages.COMMAND_EXIT.getValue());
            String commandType = reader.readLine();
            Command command = null;
            switch (commandType) {
                case "1":
                    command = new AddCommand(this.storage, this);
                    break;
                case "2":
                    command = new ModifyCommand(this.storage, this);
                    break;
                case "3":
                    command = new DeleteCommand(this.storage, this);
                    break;
                case "4":
                    command = new ViewCommand(this.storage, this);

                    break;
                case "5":
                    return;
            }

            assert command != null;
            command.execute();
        }

    }

    public void addEvent(Event event) {
        this.storage.writeEvent(event);

    }

    public void deleteEvent(String date) throws EventDoesNotExist {
        Event event = this.eventSelector.getEventByHourAndDay(date);

        Map<String, Event> events = this.storage.getAllEvents();

        events.remove(event.getDate());

        this.storage.refreshDB();

    }

    public void modifyEvent(String date) throws EventDoesNotExist, IOException, InvalidEventType, InvalidEventMarker {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Event> events = this.storage.getAllEvents();
        Event event = events.remove(date);

        System.out.println("Enter the what about the event you want to change:");
        System.out.println("1.Type");
        System.out.println("2.Date");
        System.out.println("3.Marker");
        System.out.println("4.Description");

        String choice = reader.readLine();

        switch (choice) {
            case "1":
                System.out.println("Enter event type : task or date:");
                String type = reader.readLine();

                event.setType(type);

                break;
            case "2":
                System.out.println("Enter hour and day for the event in format 1D-10H");
                String newDate = reader.readLine();

                while (events.containsKey(newDate)) {
                    System.out.println("Event with that date already exist");
                    System.out.println(events.get(newDate).toString());
                    System.out.println("Please enter new date:");
                    newDate = reader.readLine();
                }
                event.setDate(newDate);
                break;
            case "3":
                System.out.println("Enter event marker:" + System.lineSeparator()
                        + "Public" + System.lineSeparator()
                        + "Confidential" + System.lineSeparator()
                        + "Private");

                String marker = reader.readLine();

                event.setMarker(marker);

                break;
            case "4":
                System.out.println("Enter event description:");
                String description = reader.readLine();
                event.setDescription(description);
                break;
        }
        events.put(event.getDate(), event);
        this.storage.refreshDB();
    }

    public void showCalendar(String by, String value) throws EventDoesNotExist {
        Event[] events = null;
        String[] columnNames = {"Date",
                "Type",
                "Marker",
                "Description"};
        switch (by) {
            case "1":
                events = new Event[1];
                events[0] = this.eventSelector.getEventByHourAndDay(value);
                break;
            case "2":
                events = this.eventSelector.getEventsByDay(value)
                        .stream()
                        .sorted(Comparator.comparing(Event::getDate))
                        .toArray(Event[]::new);
                break;
            case "3":
                events = this.eventSelector.getEventsByMount(value)
                        .stream()
                        .sorted(Comparator.comparing(Event::getDate))
                        .toArray(Event[]::new);
                break;
            case "4":
                events = this.eventSelector.getAllEvents()
                        .stream()
                        .sorted(Comparator.comparing(Event::getDate))
                        .toArray(Event[]::new);

                break;
        }


        Object[][] data = new Object[events.length][];
        this.getData(data, events);
        if(events.length > 0) {
            TextTable textTable = new TextTable(columnNames, data);
            textTable.printTable();
        }else {
            System.out.println("No events!");
        }

    }

    private void getData(Object[][] data, Event[] events) {

        for (int i = 0; i < events.length; i++) {
            data[i] = new Object[]{
                    events[i].getDate(),
                    events[i].getType(),
                    events[i].getMarker(),
                    events[i].getDescription()
            };
        }
    }
}
