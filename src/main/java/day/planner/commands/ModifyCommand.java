package day.planner.commands;

import day.planner.data.DataStorage;
import day.planner.data.DataValidator;
import day.planner.events.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ModifyCommand extends Command {


    public ModifyCommand(DataStorage storage, EventSelector eventSelector) {
        super(storage, eventSelector);
    }

    @Override
    public void execute() throws IOException, EventDoesNotExist {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the date of the event you want to change in format 12M-12D-12H.");
        String dateOfTheEventYouWantToChange = reader.readLine();

        if (!DataValidator.validateDate(dateOfTheEventYouWantToChange)
                || !super.getStorage().getAllEvents().containsKey(dateOfTheEventYouWantToChange)) {
            throw new EventDoesNotExist("Event does not exist!");
        }

        try {

           modifyEvent(dateOfTheEventYouWantToChange);

        } catch (EventDoesNotExist | InvalidEventType | InvalidEventMarker e) {
            System.out.println(e.getMessage());
        }
    }

    private void modifyEvent(String date) throws EventDoesNotExist, IOException, InvalidEventType, InvalidEventMarker {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Event> events = this.getStorage().getAllEvents();
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
        this.getStorage().refreshDB();
    }

}
