package com.markpaveszka.jcrapp.ui.events;

import android.graphics.Bitmap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JCREvent
{
    private String name;
    private String venue;
    private String duration;
    private Date date;
    private String facebookLink;


    private Bitmap imgBitmap;

    public JCREvent(String name, String venue, String duration, String facebookLink) {
        this.name = name;
        this.venue = venue;
        this.duration = duration;
        this.facebookLink = facebookLink;
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

    public Bitmap getImgBitmap() { return imgBitmap; }

    public String getDuration() {
        return duration;
    }

    public String getFacebookLink() {
        return facebookLink;
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

    public void setImgBitmap(Bitmap imgBitmap) {
        this.imgBitmap = imgBitmap;
    }


    @Override
    public String toString()
    {
        return "Name: " + this.name + "\n" +
                "Venue: " + this.venue + "\n" +
                "Date: " + this.date.toString() + "\n" +
                "Duration: " + this.duration + "\n" +
                "Facebook: " + this.facebookLink + "\n";
    }



}
