package com.app.ebank.mbanking;

/**
 * Created by Hichem Himovic on 15/06/2017.
 */

public class ModelActu {
    private String Event;
    private String Date;
    private String Hour;
    private String Place;
    public ModelActu(String Event,String Date,String Hour,String Place){
        this.setEvent(Event);
        this.setDate(Date);
        this.setHour(Hour);
        this.setPlace(Place);
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }
}
