package day.planner.test;

import com.google.gson.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ELISAV on 4.1.2017 г..
 */
public class Test {

    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Jonh Joe");
        jsonObject.addProperty("age", 36);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonObject.toString());
        String prettyJsonString = gson.toJson(je);

        System.out.println(jsonObject.toString());
        System.out.println(prettyJsonString);

        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        try(FileWriter writer = new FileWriter("resources/info.json",true)){
            writer.write(jsonObject.toString());
            writer.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonParser js = new JsonParser();

        try (BufferedReader fl = new BufferedReader(new FileReader(new File("resources/info.json")))){

            String line = fl.readLine();
            JsonObject jsonObject1 = (JsonObject) js.parse(line);
            Person p2 = new Gson().fromJson(jsonObject1,Person.class);
            System.out.println(p2.getName() + " " + p2.getAge() + " " + p2.getDate());
            System.out.println(jsonObject1);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
