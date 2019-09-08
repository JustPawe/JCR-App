package com.markpaveszka.jcrapp.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.markpaveszka.jcrapp.R;
import com.markpaveszka.jcrapp.SpreadSheetsConnectionService;

public class EventsFragment extends Fragment {

    private EventsViewModel eventsViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventsViewModel =
                ViewModelProviders.of(this).get(EventsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        SpreadSheetsConnectionService connect = new SpreadSheetsConnectionService(getActivity(), root);
        connect.getEvents();

        return root;
    }
}