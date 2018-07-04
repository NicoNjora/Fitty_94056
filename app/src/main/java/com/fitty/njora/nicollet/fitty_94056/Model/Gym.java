package com.fitty.njora.nicollet.fitty_94056.Model;

public class Gym {

    private String name;
    private String opening_hrs;
    private String closing_hrs;
    private int longitude;
    private int latitude;

    public Gym(String name, String opening_hrs, String closing_hrs, int longitude, int latitude){

        this.name = name;
        this.opening_hrs = opening_hrs;
        this.closing_hrs = closing_hrs;
        this.longitude = longitude;
        this.latitude = latitude;
    }

//    Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    Opening hrs
    public String getOpening_hrs() {
        return opening_hrs;
    }

    public void setOpening_hrs(String opening_hrs) {
        this.opening_hrs = opening_hrs;
    }
//    Closing hrs
    public String getClosing_hrs() {
        return closing_hrs;
    }

    public void setClosing_hrs(String closing_hrs) {
        this.closing_hrs = closing_hrs;
    }
//    Longitude
    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }
//    Latitude
    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

}
