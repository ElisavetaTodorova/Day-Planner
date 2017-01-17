package day.planner.engine;

import day.planner.commands.*;
import day.planner.data.DataStorage;
import day.planner.events.EventDoesNotExist;
import day.planner.events.EventSelector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
                    command = new AddCommand(this.storage, this.eventSelector);
                    break;
                case "2":
                    command = new ModifyCommand(this.storage, this.eventSelector);
                    break;
                case "3":
                    command = new DeleteCommand(this.storage, this.eventSelector);
                    break;
                case "4":
                    command = new ViewCommand(this.storage, this.eventSelector);
                    break;
                case "5":
                    return;
            }


            assert command != null;
            command.execute();
        }

    }
}
