package com.androidy.forgetmenot.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidy.forgetmenot.R;
import com.androidy.forgetmenot.adapter.SavedPersonAdapter;
import com.androidy.forgetmenot.customclass.Person;
import com.androidy.forgetmenot.sharedpreference.SharedPreference;

import java.util.ArrayList;

public class HomeScreenActivity  extends ListActivity {

    private Person[] personArray;
    private ArrayList<Person> personArrayList;
    private SharedPreference mSharedPreference;
    private Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


                int  i = SavedPersonAdapter.selectedPosition;




        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  i = SavedPersonAdapter.selectedPosition;
                Toast.makeText(HomeScreenActivity.this , i + "" , Toast.LENGTH_SHORT).show();
                mSharedPreference.removeFavorite(HomeScreenActivity.this , i);

                updateList();

            }
        });

        updateList();



    }

    private void updateList() {

        mSharedPreference = new SharedPreference();
        personArrayList = mSharedPreference.getFavorites(HomeScreenActivity.this);

        if (personArrayList == null) {
            Toast.makeText(HomeScreenActivity.this , "Empty" , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HomeScreenActivity.this , "Full" , Toast.LENGTH_SHORT).show();


            personArray = new Person[personArrayList.size()];
            personArray = personArrayList.toArray(personArray);


            SavedPersonAdapter adapter = new SavedPersonAdapter(this, personArray);
            setListAdapter(adapter);

            int i = SavedPersonAdapter.selectedPosition;

            Toast.makeText(HomeScreenActivity.this, i + "", Toast.LENGTH_SHORT).show();
        }
    }

    private void SetSelectedPerson() {


    }
}


