package day.planner.data;

import day.planner.events.InvalidEventType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ELISAV on 12.1.2017 г..
 */
public class DataValidatorTest {

    @Test
    public void dateFormatValidate(){
        String date = "12M-12D-12H";

        boolean result = DataValidator.dateFormatValidate(date);

        Assert.assertEquals(true,result);
    }

    @Test
    public void validateInvalidDateConstrains(){
        String date = "13M-21D-16H";

        boolean result = DataValidator.validateDate(date);

        Assert.assertEquals(false,result);
    }

    @Test
    public void validateValidDateConstrains(){
        String date = "12M-21D-16H";

        boolean result = DataValidator.validateDate(date);

        Assert.assertEquals(true,result);
    }

    @Test(expected = InvalidEventType.class)
    public void validateInvalidEventType() throws InvalidEventType {
        String type = "chubaka";

        DataValidator.eventTypeValidate(type);
    }

    @Test
    public void validateValidEventType() {
        String type = "Task";
        boolean result = false;
        try {
            result =  DataValidator.eventTypeValidate(type);
        } catch (InvalidEventType e) {
            System.out.println(e.getMessage());
        }

        Assert.assertEquals(true,result);
    }



}