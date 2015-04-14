package com.androidy.forgetmenot.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidy.forgetmenot.customclass.Person;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by christinajackey on 3/12/15.
 */
public class SharedPreference {

        public static final String PREFS_NAME = "PRODUCT_APP";
        public static final String LOVEDLIST = "List_Of_Loved";

    public static final String SELECTED_PERSON_SHARED = "selected_person";

        public SharedPreference() {
            super();
        }

        // This four methods are used for maintaining favorites.
        public void saveFavorites(Context context, List<Person> favorites) {
            SharedPreferences settings;
            SharedPreferences.Editor editor;

            settings = context.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);
            editor = settings.edit();

            Gson gson = new Gson();
            String jsonFavorites = gson.toJson(favorites);

            editor.putString(LOVEDLIST, jsonFavorites);

            editor.commit();
        }

        public void addFavorite(Context context, Person person) {
            List<Person> favorites = getFavorites(context);
            if (favorites == null)
                favorites = new ArrayList<Person>();
            favorites.add(person);
            saveFavorites(context, favorites);
        }
        // Person person

        public void removeFavorite(Context context, int i) {
            ArrayList<Person> favorites = getFavorites(context);
            if (favorites != null) {
                // favorites.remove(person);
                favorites.remove(i);
                saveFavorites(context, favorites);
            }
        }

        public ArrayList<Person> getFavorites(Context context) {
            SharedPreferences settings;
            List<Person> favorites;

            settings = context.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);

            if (settings.contains(LOVEDLIST)) {
                String jsonFavorites = settings.getString(LOVEDLIST, null);
                Gson gson = new Gson();
                Person[] favoriteItems = gson.fromJson(jsonFavorites,
                        Person[].class);

                favorites = Arrays.asList(favoriteItems);
                favorites = new ArrayList<Person>(favorites);
            } else
                return null;

            return (ArrayList<Person>) favorites;
        }



    public void selectLovedOne(Context context, Person selected) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(SELECTED_PERSON_SHARED,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(selected);

        editor.putString("selected_loved_one", jsonFavorites);

        editor.apply();
    }
    public void removeOldSelectedPerson(Context context) {

        SharedPreferences settings = context.getSharedPreferences(SELECTED_PERSON_SHARED,
                Context.MODE_PRIVATE);
        settings.edit().remove(SELECTED_PERSON_SHARED);
        settings.edit().apply();


    }

    public Person getSelectedPerson(Context context) {
        SharedPreferences settings;
        Person person = new Person();


        settings = context.getSharedPreferences(SELECTED_PERSON_SHARED,
                Context.MODE_PRIVATE);

        if (settings.contains("selected_loved_one")) {
            String jsonFavorites = settings.getString("selected_loved_ones", null);
            Gson gson = new Gson();
            Person selectedPerson = gson.fromJson(jsonFavorites,
                    Person.class);


        } else
            return null;

        return person;
    }




}

