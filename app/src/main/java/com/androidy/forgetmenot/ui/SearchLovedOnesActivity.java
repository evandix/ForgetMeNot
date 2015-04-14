package com.androidy.forgetmenot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidy.forgetmenot.R;


public class SearchLovedOnesActivity extends ActionBarActivity {

    private Button searchButton;

    private EditText firstNameEditText;
    private EditText lastNameEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_loved_ones);

        firstNameEditText = (EditText) findViewById(R.id.firstName);
        lastNameEditText = (EditText) findViewById(R.id.lastName);

        searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();

                Intent intent = new Intent(SearchLovedOnesActivity.this , AddLovedOneToListActivity.class);
                intent.putExtra(getString(R.string.first_name) , firstName);
                intent.putExtra(getString(R.string.last_name) , lastName);
                startActivity(intent);
            }
        });
    }

}
