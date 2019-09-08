package com.markpaveszka.jcrapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.markpaveszka.jcrapp.ui.events.Event;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SpreadSheetsConnectionService {

    private FragmentActivity root;
    private View view;
    private ArrayList<Event> events;
    private ImageView testIV;


    public SpreadSheetsConnectionService(FragmentActivity root, View view)
    {
        this.root = root;
        this.view = view;
    }

    public void getEvents()
    {
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory factory = JacksonFactory.getDefaultInstance();
        final Sheets sheetsService = new Sheets.Builder(transport, factory, null)
                .setApplicationName("My Awesome App")
                .build();
        final String spreadsheetId = Config.spreadsheet_id;

        events = new ArrayList<>();
        testIV = view.findViewById(R.id.testIV);

        if (testIV == null)
        Log.i("test", "true");


        new Thread() {
            @Override
            public void run() {
                try {
                    String range = "Sheet1!A1:F";
                    ValueRange result = sheetsService.spreadsheets().values()
                            .get(spreadsheetId, range)
                            .setKey(Config.google_api_key)
                            .execute();
                    int numRows = result.getValues() != null ? result.getValues().size() : 0;
                    Log.d("SUCCESS.", "rows retrived " + numRows);
                    for (int i=1; i<numRows; i++)
                    {
                        events.add(new Event(
                                result.getValues().get(i).get(0).toString(),
                                result.getValues().get(i).get(1).toString(),
                                result.getValues().get(i).get(4).toString(),
                                result.getValues().get(i).get(5).toString()));
                        events.get(i-1).setDateTime(
                                result.getValues().get(i).get(2).toString(),
                                result.getValues().get(i).get(3).toString()
                        );
                    }
                    URL newurl = new URL(events.get(0).getImgUrl());
                    final Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    root.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            testIV.setImageBitmap(mIcon_val);
                        }
                    });

                    Log.d("result", result.getValues().get(1).get(0).toString());
                    Log.i("event", events.get(0).toString());
                }
                catch (IOException e) {
                    Log.e("Sheets failed", e.getLocalizedMessage());
                }
            }
        }.start();
    }
}