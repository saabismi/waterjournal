package com.example.waterjournal.ui.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.waterjournal.R;

/**
 * Tips fragment show user some valuable information about water.
 * Information texts are in boxes and boxes are in scrollView
 * so user can scroll texts up and down.
 * Texts are hard coded in string.xml.
 */
public class TipsFragment extends Fragment {
    /**
     * Widget: a Button
     */
    private Button tipsBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View tips = inflater.inflate(R.layout.fragment_tips, container, false);

        /**
         * Button to go back to the home fragment
         */
        tipsBtn = tips.findViewById(R.id.imageButtonTips);
        tipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OnClick to use navigation controller to go to the home fragment
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            }
        });

        return tips;
    }
}
