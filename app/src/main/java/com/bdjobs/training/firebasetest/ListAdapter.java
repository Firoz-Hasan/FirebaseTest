package com.bdjobs.training.firebasetest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FIROZ HASAN on 3/10/2018.
 */

public class ListAdapter extends ArrayAdapter {
    ArrayList<Users> usersList = new ArrayList<Users>();
    Context context;
    LayoutInflater inflater;

    public ListAdapter(@NonNull Context context, @NonNull ArrayList<Users> usersList) {
        super(context, R.layout.customlist, usersList);
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //  return super.getView(position, convertView, parent);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.customlist, parent, false);
            viewHolder.usernameTV = convertView.findViewById(R.id.usernameTV);
            viewHolder.passwordTV = convertView.findViewById(R.id.passwordTV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.usernameTV.setText(usersList.get(position).getUsername());
        viewHolder.passwordTV.setText(usersList.get(position).getPassword());
        return convertView;


    }

    public static class ViewHolder {
        TextView usernameTV;
        TextView passwordTV;


    }
}
