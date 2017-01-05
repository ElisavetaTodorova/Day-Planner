package day.planner.models;

import day.planner.interfaces.IOParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ELISAV on 4.1.2017 г..
 */
public class ConsoleParser implements IOParser {

    public ConsoleParser() {

    }

    @Override
    public String read() {
        String input = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    @Override
    public void write(String output) {
        System.out.println(output);

    }
}
