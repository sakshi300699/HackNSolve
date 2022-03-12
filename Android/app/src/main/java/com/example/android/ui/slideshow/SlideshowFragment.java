package com.example.android.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.CancerDetectActivity;
import com.example.android.PeriodTrackerActivity;
import com.example.android.R;
import com.example.android.RegisterActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.core.operation.AckUserWrite;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        MaterialButton periodTracker = root.findViewById(R.id.trackPeriod);
        MaterialButton cancerDetect = root.findViewById(R.id.cancerDetection);
        periodTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PeriodTrackerActivity.class);
                startActivity(intent);
            }
        });
        cancerDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CancerDetectActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

}