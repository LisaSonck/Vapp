package no.nordicsemi.android.nrftoolbox;

import java.text.DateFormat;

public class DatabaseModel {
    private int id;
    private String name;
    //private DateFormat current_date;
    private String activity;
    private String data;

    //constructors

    public DatabaseModel(int id, String name, String activity, String data) {
        this.id = id;
        this.name = name;
        //this.current_date = current_date;
        this.activity = activity;
        this.data = data;
    }

    public DatabaseModel(){
    }

    // toString
    @Override
    public String toString() {
        return "DatabaseFragment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activity='" + activity + '\'' +
                ", data='" + data + '\'' +
                '}';
    }


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public DateFormat getCurrent_date() { return current_date; }

    //public void setCurrent_date(DateFormat current_date) { this.current_date = current_date; }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
