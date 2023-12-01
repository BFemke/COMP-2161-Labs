package emke.comp2161.simpletourguideapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


public class CityListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public CityListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        return view;
    }

    //Sets up the list element with array of city names from strings.xml
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.cities,
                android.R.layout.simple_list_item_1);

        setListAdapter(arrayAdapter);

        //Sets up list click listener
        getListView().setOnItemClickListener(this);
    }

    //Handles selection of list items sends data to main activity
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MainActivity mainActivity = (MainActivity) getActivity();

        mainActivity.takeData(i); //Calls main activity takeData method in order to update description
    }
}