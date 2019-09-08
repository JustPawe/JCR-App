package com.markpaveszka.jcrapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event
{
    private String name;
    private String venue;
    private String duration;
    private Date date;
    private String imgUrl;

    public Event(String name, String venue, String duration, String imgUrl) {
        this.name = name;
        this.venue = venue;
        this.duration = duration;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public Date getDate() {
        return date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDateTime(String date, String startTime)
    {
        String [] dateSplit = date.split("/");
        String strDate = dateSplit[2] + "-"+dateSplit[1] + "-" + dateSplit[0];
        strDate = strDate + "T" + startTime +":00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            this.date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return "Name: " + this.name + "\n" +
                "Venue: " + this.venue + "\n" +
                "Date: " + this.date.toString() + "\n" +
                "Duration: " + this.duration + "\n" +
                "IMG URL: " + this.imgUrl + "\n";
    }


}
