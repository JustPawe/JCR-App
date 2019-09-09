package com.markpaveszka.jcrapp.ui.sports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.markpaveszka.jcrapp.R;

public class SportsFragment extends Fragment {

    private SportsViewModel sportsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sportsViewModel =
                ViewModelProviders.of(this).get(SportsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sports, container, false);

        return root;
    }
}