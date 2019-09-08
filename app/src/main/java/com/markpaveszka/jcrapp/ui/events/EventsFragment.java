package com.markpaveszka.jcrapp.ui.events;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.markpaveszka.jcrapp.Config;
import com.markpaveszka.jcrapp.R;

import java.io.IOException;
import java.util.ArrayList;

public class EventsFragment extends Fragment {

    private EventsViewModel eventsViewModel;
    private ArrayList<Event> events;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventsViewModel =
                ViewModelProviders.of(this).get(EventsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory factory = JacksonFactory.getDefaultInstance();
        final Sheets sheetsService = new Sheets.Builder(transport, factory, null)
                .setApplicationName("My Awesome App")
                .build();
        final String spreadsheetId = Config.spreadsheet_id;

        events = new ArrayList<>();

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
                    Log.d("result", result.getValues().get(1).get(0).toString());
                    Log.i("event", events.get(0).toString());
                }
                catch (IOException e) {
                    Log.e("Sheets failed", e.getLocalizedMessage());
                }
            }
        }.start();
        return root;
    }
}