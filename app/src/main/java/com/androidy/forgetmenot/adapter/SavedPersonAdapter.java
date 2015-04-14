package com.androidy.forgetmenot.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.androidy.forgetmenot.R;
import com.androidy.forgetmenot.customclass.Person;
import com.androidy.forgetmenot.sharedpreference.SharedPreference;

/**
 * Created by christinajackey on 3/16/15.
 */
public class SavedPersonAdapter extends BaseAdapter {

    Context mContext;
    Person[] mPersonArray;
    public static int selectedPosition =   -1;
    public final static String TAG = PersonAdapter.class.getSimpleName();
    SharedPreference mSharedPreference;



    public SavedPersonAdapter(Context context , Person[] persons) {
        mContext = context;
        mPersonArray = persons;

    }

    @Override
    public int getCount() {
        return mPersonArray.length;
    }

    @Override
    public Object getItem(int position) {
        return mPersonArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;        // we wont use
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.person_list_item , null);
            holder = new ViewHolder();
            holder.nameText = (TextView) convertView.findViewById(R.id.nameTextView);
            holder.birthDateText = (TextView) convertView.findViewById(R.id.birthDateTextView);
            holder.checkBoxList= (CheckBox) convertView.findViewById(R.id.checkBox);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Person personForList = mPersonArray[position];

        holder.nameText.setText(personForList.getName());
        holder.birthDateText.setText(personForList.getBirthDate());
        holder.checkBoxList.setChecked(position == selectedPosition);
        holder.checkBoxList.setTag(position);
        holder.checkBoxList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedPosition = (Integer) view.getTag();

                notifyDataSetInvalidated();
                notifyDataSetChanged();

                Log.v(TAG, selectedPosition + "");

                  Person selectedPerson = mPersonArray[selectedPosition];
              SharedPreference sharedPreference = new SharedPreference();
                sharedPreference.removeOldSelectedPerson(mContext);
                sharedPreference.selectLovedOne(mContext , selectedPerson);







            }
        });







        return convertView;
    }

    public static class ViewHolder {
        TextView nameText;
        TextView birthDateText;
        CheckBox checkBoxList;
    }


}


