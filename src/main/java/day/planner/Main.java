package day.planner;

import day.planner.engine.ProcessManager;
import day.planner.events.EventDoesNotExist;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws EventDoesNotExist {
        ProcessManager manager = new ProcessManager();
        try {
            manager.run();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}
