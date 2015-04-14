package com.androidy.forgetmenot.ui;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidy.forgetmenot.R;
import com.androidy.forgetmenot.adapter.PersonAdapter;
import com.androidy.forgetmenot.customclass.Person;
import com.androidy.forgetmenot.sharedpreference.SharedPreference;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

   public class  AddLovedOneToListActivity extends ListActivity {

            private String mFirstName;
            private String mName;
       private String mLastName;

            private ArrayList<Person> mPersonArrayList;

            public static final String TAG = AddLovedOneToListActivity.class.getSimpleName();


            Button addMyLovedList;

            private Person[] personArray;

            SharedPreference mSharedPreference = new SharedPreference();



            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_add_loved_one_to_list);

                Intent intent = getIntent();
                mFirstName =  intent.getStringExtra(getString(R.string.first_name));
                mLastName = intent.getStringExtra(getString(R.string.last_name));
                mName = mFirstName + " " + mLastName;

                getNameResults();

                addMyLovedList = (Button) findViewById(R.id.toastButton);
                addMyLovedList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Person person = personArray[selectedPositionNumber];
                        //  String bio =  person.getBio();

                        //  Toast.makeText( NameListResults.this , bio , Toast.LENGTH_SHORT).show();
                        int i = PersonAdapter.selectedPosition;

                        Person person = personArray[i];
                        String bio = person.getBio();
                        Toast.makeText(AddLovedOneToListActivity.this, bio + "", Toast.LENGTH_SHORT).show();

                        mSharedPreference.addFavorite(AddLovedOneToListActivity.this , person);

                        Intent intent = new Intent(AddLovedOneToListActivity.this , HomeScreenActivity.class);
                        startActivity(intent);


                    }
                });

            }






            private void getNameResults() {

                String url = "http://fast-lowlands-9315.herokuapp.com/people.json";

                OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(url)
                        .build();

                Call call = client.newCall(request);
                call.enqueue( new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        final String jsonData = response.body().string();

                        if (response.isSuccessful()) {

                            try {
                                mPersonArrayList = getPersonArrayList(jsonData);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AddLovedOneToListActivity.this , jsonData , Toast.LENGTH_SHORT).show();
                                updateDisplay();

                            }
                        });

                    }
                });
            }

            private void updateDisplay() {

        /*
        ArrayList<String> stockList = new ArrayList<String>();
stockList.add("stock1");
stockList.add("stock2");
String[] stockArr = new String[stockList.size()];
stockArr = stockList.toArray(stockArr);


         */

                personArray = new Person[mPersonArrayList.size()];
                personArray = mPersonArrayList.toArray(personArray);

                PersonAdapter personAdapter = new PersonAdapter(AddLovedOneToListActivity.this , personArray);
                setListAdapter(personAdapter);



            }



            private ArrayList<Person> getPersonArrayList(String jsonData) throws JSONException{

                JSONArray jsonArray = new JSONArray(jsonData);

                ArrayList<Person> persons = new ArrayList<>();

                for (int i = 0 ; i < jsonArray.length() ; i ++ ) {
                    JSONObject jsonPerson = jsonArray.getJSONObject(i);
                    Person person = new Person();

                    String name = jsonPerson.getString("name");

                    if (mName.equals(name) || mName.contains(name) || mName.equalsIgnoreCase(name)) {
                        person.setName(jsonPerson.getString("name"));
                        person.setBio(jsonPerson.getString("bio"));
                        person.setBirthDate(jsonPerson.getString("birth_date"));
                        person.setGps(jsonPerson.getString("gps"));
                        person.setId(jsonPerson.getInt("id"));


                        persons.add(person);

                        Log.v(TAG, i + "");
                    } else {

                    }
                }
                return persons;
            }



        }









