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
 * History fragment will show user's daily drinking history on a listView. User can check specific days by clicking dates and get specific information about them.
 */
public class HistoryFragment extends Fragment {
    /**
     * Creating private and public variables for HistoryFragment class.
     * listViewForDays variable is for ListView UI element with id listViewForDays.
     * historyText variable is for TextView UI element with id historyText.
     * tips variable is for tips button element.
     * EXTRA variable is a key for adding information using intent for SpecificDayView class.
     */
    private ListView listViewForDays;
    private TextView historyText;
    private Button tips;
    public static final String EXTRA = "ListPosition";

    /**
     * This View is used for creating HistoryFragment activity. It will show activity's view which is app's logo, textView with information,
     * listView with a list of user's days which user has been drinking water and tips button which will show useful information about water.
     * Called to have the fragment instantiate its user interface view.
     * @param inflater the LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container used to generate the LayoutParams of the view.
     * @param savedInstanceState this fragment is being re-constructed from a previous saved state as given here.
     * @return return the View for the fragment's UI, or null.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View hist = inflater.inflate(R.layout.fragment_history, container, false);
        this.listViewForDays = hist.findViewById(R.id.listViewForDays);
        this.historyText = hist.findViewById(R.id.historyText);
        this.historyText.setText("Your drinking history.");

        //Creating listView for HistoryFragment.
        this.listViewForDays.setAdapter(new ArrayAdapter<WaterObject>(getActivity(), android.R.layout.simple_list_item_1, DailyDrinkingObject.getInstance().getDailyWaterList()));
        this.listViewForDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * This onItemClick function will be used to create new activity on SpecificDayView class. It will also add information to the class and keep
             * track which day is selected and show correct day.
             * @param parent This parameter will tell where specific click happened.
             * @param view This parameter will tell in which view the click happened.
             * @param position This parameter will tell which position the view is on the adapter.
             * @param id This parameter will tell which is the row position of the clicked view.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent to open daily view to check information from a specific day
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
            /**
             * This function is for water tips fragment view. It will go to water information fragment when clicked.
             * @param view This parameter will tell from which view are we going to water information view.
             */
            @Override
            public void onClick(View view) {
                //OnClick to use navigation controller to go to the tips fragment.
                Navigation.findNavController(view).navigate(R.id.navigation_tips);
            }
        });
        return hist;
    }
}