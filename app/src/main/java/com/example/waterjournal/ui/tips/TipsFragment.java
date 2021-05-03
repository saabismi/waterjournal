package com.example.waterjournal.ui.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.waterjournal.R;

public class TipsFragment extends Fragment {
private TextView tv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View tips = inflater.inflate(R.layout.fragment_tips, container, false);
        tv = tips.findViewById(R.id.textView);
        tv.setText("This is a tips fragment!");

        return tips;
    }
}
