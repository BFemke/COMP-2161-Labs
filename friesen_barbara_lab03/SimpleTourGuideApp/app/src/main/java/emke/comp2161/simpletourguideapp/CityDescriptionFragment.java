package emke.comp2161.simpletourguideapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CityDescriptionFragment extends Fragment {

    TextView description;

    public CityDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_description, container, false);

        description = view.findViewById(R.id.cityDescription);

        //Sets initial welcome text
        description.setText(R.string.welcome);

        return view;

    }

    public void updateText(String city){

        //Sets the appropriate description based on city name given
        if(city.equals("Calgary")){
            description.setText(R.string.Calgary);
        }
        else if(city.equals("Quebec")){
            description.setText(R.string.Quebec);
        }
        else if(city.equals("Edmonton")){
            description.setText(R.string.Edmonton);
        }
        else if(city.equals("Vancouver")){
            description.setText(R.string.Vancouver);
        }
        else if(city.equals("Victoria")){
            description.setText(R.string.Victoria);
        }
        else if(city.equals("Toronto")){
            description.setText(R.string.Toronto);
        }

    }

}