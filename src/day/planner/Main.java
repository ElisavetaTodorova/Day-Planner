package day.planner;

import day.planner.models.ProcessManager;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        ProcessManager manager = new ProcessManager();
        try {
            manager.run();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
