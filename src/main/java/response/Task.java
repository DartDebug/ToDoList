package response;

import java.util.Date;

public class Task {
    private int Id;
    private String date;
    private String thingToDo;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThingToDo() {
        return thingToDo;
    }

    public void setThingToDo(String thingToDo) {
        this.thingToDo = thingToDo;
    }
}
