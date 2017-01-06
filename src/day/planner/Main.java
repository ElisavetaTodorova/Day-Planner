package day.planner;

import day.planner.models.ProcessManager;

import java.io.IOException;

/**
 * Created by ELISAV on 4.1.2017 г..
 */
public class Main {

    public static void main(String[] args) {
        ProcessManager manager = new ProcessManager();
        try {
            manager.run();
        } catch (IOException e) {
            e.printStackTrace();

        }

//        try {
//            Event e1 = new Event("date","12D-13H","public","hdjfidfi");
//            Event e2 = new Event("date","12D-13H","public","hdjfidfi");
//
//            System.out.println(e1.equals(e2));
//        } catch (InvalidEventType invalidEventType) {
//            invalidEventType.printStackTrace();
//        }


//        String[] x = "12D-12H".split("-");
//        System.out.println(x[0]);
    }

}
