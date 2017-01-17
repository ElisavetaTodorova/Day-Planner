package day.planner.commands;

import day.planner.data.DataStorage;
import day.planner.events.Event;
import day.planner.events.EventDoesNotExist;
import day.planner.events.EventSelector;
import dnl.utils.text.table.TextTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class ViewCommand extends Command {


    public ViewCommand(DataStorage storage, EventSelector eventSelector) {
        super(storage, eventSelector);
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1.For hour.");
        System.out.println("2.For whole day.");
        System.out.println("3.For mount.");
        System.out.println("4.All evens.");
        System.out.println("Enter your choice by choosing between number 1 to 4.");
        String choice = reader.readLine();
        String value = null;
        switch (choice) {
            case "1":
                System.out.println("Enter hour and day int the format 12M-12D-12H.");
                value = reader.readLine();
                break;
            case "2":
                System.out.println("Enter day of mount in the format 12D.");
                value = reader.readLine();
                break;
            case "3":
                System.out.println("Enter mount in the format 12M.");
                value = reader.readLine();
                break;

        }
        try {
            showCalendar(choice, value);
        } catch (EventDoesNotExist e) {
            System.out.println(e.getMessage());
        }

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
                events[0] = this.getEventSelector().getEventByHourAndDay(value);
                break;
            case "2":
                events = this.getEventSelector().getEventsByDay(value)
                        .stream()
                        .sorted(Comparator.comparing(Event::getDate))
                        .toArray(Event[]::new);
                break;
            case "3":
                events = this.getEventSelector().getEventsByMount(value)
                        .stream()
                        .sorted(Comparator.comparing(Event::getDate))
                        .toArray(Event[]::new);
                break;
            case "4":
                events = this.getEventSelector().getAllEvents()
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
