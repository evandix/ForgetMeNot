package com.androidy.forgetmenot.customclass;

/**
 * Created by christinajackey on 4/13/15.
 */
public class Person {
    private int mId;
    private String mName;
    private String mBirthDate;
    private String mBio;
    private String mGps;

    public Person() {}

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getBirthDate() {
        return mBirthDate;
    }

    public void setBirthDate(String birthDate) {
        mBirthDate = birthDate;
    }

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public String getGps() {
        return mGps;
    }

    public void setGps(String gps) {
        mGps = gps;
    }
}