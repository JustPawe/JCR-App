package com.markpaveszka.jcrapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.markpaveszka.jcrapp.R;
import com.markpaveszka.jcrapp.SpreadSheetsConnectionService;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    private Button prevPicBtn;
    private Button nextPicBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final SpreadSheetsConnectionService connectionService = new SpreadSheetsConnectionService(getActivity(), root);
        connectionService.getGallery();
        prevPicBtn = (Button) root.findViewById(R.id.previousBtn);
        nextPicBtn = (Button) root.findViewById(R.id.nextBtn);

        prevPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectionService.previousPicture();
            }
        });

        nextPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectionService.nextPicture();
            }
        });
        return root;
    }
}