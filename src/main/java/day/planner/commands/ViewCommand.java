package day.planner.commands;

import day.planner.data.DataStorage;
import day.planner.events.EventDoesNotExist;
import day.planner.engine.ProcessManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewCommand extends Command {

    public ViewCommand(DataStorage storage, ProcessManager manager) {
        super(storage, manager);
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
            super.getManager().showCalendar(choice, value);
        } catch (EventDoesNotExist e) {
            System.out.println(e.getMessage());
        }

    }
}
