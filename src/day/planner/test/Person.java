package day.planner.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ELISAV on 4.1.2017 г..
 */
public class Person {
    private String name;
    private int age;
    private String date;

    private Person(String name, int age, String date) {
        this.setName(name);
        this.setAge(age);
        this.setDate(date);

    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        this.date = date;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }
}
