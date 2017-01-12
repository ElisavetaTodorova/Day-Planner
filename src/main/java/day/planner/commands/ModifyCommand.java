package day.planner.commands;

import day.planner.data.DataStorage;
import day.planner.data.DataValidator;
import day.planner.events.EventDoesNotExist;
import day.planner.events.InvalidEventMarker;
import day.planner.events.InvalidEventType;
import day.planner.engine.ProcessManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ELISAV on 11.1.2017 г..
 */
public class ModifyCommand extends Command {


    public ModifyCommand(DataStorage storage, ProcessManager manager) {
        super(storage, manager);
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

            super.getManager().modifyEvent(dateOfTheEventYouWantToChange);

        } catch (EventDoesNotExist | InvalidEventType | InvalidEventMarker e) {
            System.out.println(e.getMessage());
        }
    }
}
