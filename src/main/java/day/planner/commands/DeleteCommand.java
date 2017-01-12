package day.planner.commands;

import day.planner.data.DataStorage;
import day.planner.events.EventDoesNotExist;
import day.planner.engine.ProcessManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ELISAV on 10.1.2017 г..
 */
public class DeleteCommand extends Command {


    public DeleteCommand(DataStorage storage, ProcessManager manager) {
        super(storage, manager);
    }

    @Override
    public void execute() throws IOException, EventDoesNotExist {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter date of the event you want to delete:");
        String eventDate = reader.readLine();
        super.getManager().deleteEvent(eventDate);
    }
}
