package com.example.waterjournal.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.waterjournal.DailyDrinkingObject;
import com.example.waterjournal.HistoryActivity;
import com.example.waterjournal.R;
import com.example.waterjournal.SpecificDayView;
import com.example.waterjournal.WaterObject;

/**
 * History fragment show user's drinking history on listView by daily
 */
public class HistoryFragment extends Fragment {

    private ListView listView;
    public static final String EXTRA = "ListPosition";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View hist = inflater.inflate(R.layout.fragment_history, container, false);
        /**
         * ListeView to list days when user have use app and store a drink or drinks
         */
        this.listView = hist.findViewById(R.id.listViewForDays);
        this.listView.setAdapter(new ArrayAdapter<WaterObject>(getActivity(), android.R.layout.simple_list_item_1, DailyDrinkingObject.getInstance().getDailyWaterList()));
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent to open daily view to check stats from a specific day
                Intent specificDayView = new Intent(getActivity(), SpecificDayView.class);
                specificDayView.putExtra(EXTRA, position);
                startActivity(specificDayView);
            }
        });

        return hist;
    }
}