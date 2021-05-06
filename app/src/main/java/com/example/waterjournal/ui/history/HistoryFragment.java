package com.example.waterjournal.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.waterjournal.DailyDrinkingObject;
import com.example.waterjournal.HistoryActivity;
import com.example.waterjournal.R;
import com.example.waterjournal.SpecificDayView;
import com.example.waterjournal.WaterObject;

/**
 * History fragment show user's drinking history on listView by daily.
 * User can open a specific day and get information about that day.
 */
public class HistoryFragment extends Fragment {

    private ListView listViewForDays;
    private TextView historyText;
    private Button tips;
    public static final String EXTRA = "ListPosition";

    /**
     * Called to have the fragment instantiate its user interface view.
     * @param inflater the LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container used to generate the LayoutParams of the view.
     * @param savedInstanceState this fragment is being re-constructed from a previous saved state as given here.
     * @return return the View for the fragment's UI, or null.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View hist = inflater.inflate(R.layout.fragment_history, container, false);
        /**
         * ListView to show days when user have store a drink with onItemClick adapter.
         */
        this.listViewForDays = hist.findViewById(R.id.listViewForDays);
        this.listViewForDays.setAdapter(new ArrayAdapter<WaterObject>(getActivity(), android.R.layout.simple_list_item_1, DailyDrinkingObject.getInstance().getDailyWaterList()));
        this.listViewForDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent to open daily view activity to check stats from a specific day
                Intent specificDayView = new Intent(getActivity(), SpecificDayView.class);
                specificDayView.putExtra(EXTRA, position);
                startActivity(specificDayView);
            }
        });
        /**
         * Button to go to the tips fragment.
         */
        tips = hist.findViewById(R.id.imageButtonTips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OnClick to use navigation controller to go to the tips fragment.
                Navigation.findNavController(view).navigate(R.id.navigation_tips);
            }
        });
        return hist;
    }
}