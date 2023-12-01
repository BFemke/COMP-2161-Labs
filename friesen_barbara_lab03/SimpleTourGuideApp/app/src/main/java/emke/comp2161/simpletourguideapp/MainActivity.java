package emke.comp2161.simpletourguideapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Creates and adds both fragments to designated frames
        CityListFragment cityListFragment = new CityListFragment();
        fragmentTransaction.add(R.id.cityListFrame, cityListFragment);
        CityDescriptionFragment cityDescriptionFragment = new CityDescriptionFragment();
        fragmentTransaction.add(R.id.descriptionFrame, cityDescriptionFragment);

        fragmentTransaction.commit();
    }

    //Takes position input from list array of what item was selected
    public  void takeData(int i){
        String[] cities = getResources().getStringArray(R.array.cities);
        city = cities[i];   //gets corresponding string for list item position

        FragmentManager fragmentManager = getSupportFragmentManager();
        CityDescriptionFragment cityDescriptionFragment = (CityDescriptionFragment)
                fragmentManager.findFragmentById(R.id.descriptionFrame);

        //calls to update the city description based on city name recieved
        cityDescriptionFragment.updateText(city);




    }

    //Saves current city to be described if screen orientation changes
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("cityName",city);
    }

    //Recalls description fragment's updateText method using the restore city name
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        CityDescriptionFragment cityDescriptionFragment = (CityDescriptionFragment)
                fragmentManager.findFragmentById(R.id.descriptionFrame);

        city = savedInstanceState.getString("cityName");
        cityDescriptionFragment.updateText(city);
    }
}