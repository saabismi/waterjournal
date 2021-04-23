package com.example.waterjournal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.waterjournal.R;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_home, container, false);

        /**
         * Button to add amount of drink to store
         */
        Button addDrink = main.findViewById(R.id.buttonAddDrink);

        /**
         * Button to remove resent drink from store
         */
        Button removeResent = main.findViewById(R.id.buttonRemoveResent);
        return main;
    }
}