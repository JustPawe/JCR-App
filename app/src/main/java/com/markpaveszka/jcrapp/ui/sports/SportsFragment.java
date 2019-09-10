package com.markpaveszka.jcrapp.ui.sports;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.markpaveszka.jcrapp.R;

public class SportsFragment extends Fragment {

    private SportsViewModel sportsViewModel;
    private Button formBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sportsViewModel =
                ViewModelProviders.of(this).get(SportsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sports, container, false);
        formBtn = (Button) root.findViewById(R.id.formBtn);
        formBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdxTmhQpu35jEWjNAsScgam0ma4rKrJ0fvrx2jS-shAvcr5iA/viewform"));
                startActivity(browserIntent);
            }
        });


        return root;
    }
}