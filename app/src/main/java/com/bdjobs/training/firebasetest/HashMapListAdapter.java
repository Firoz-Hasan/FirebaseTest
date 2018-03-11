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
import java.util.HashMap;
import java.util.List;

/**
 * Created by FIROZ HASAN on 3/11/2018.
 */

public class HashMapListAdapter extends ArrayAdapter {

    LayoutInflater layoutInflater;
    Context contxt;
    ArrayList<HashMap<String, String>> userLst= new ArrayList<HashMap<String, String>>();


    public HashMapListAdapter(@NonNull Context context, @NonNull ArrayList<HashMap<String, String>> userList) {
        super(context,R.layout.customlist, userList);
        this.contxt =  context;
        this.userLst = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // return super.getView(position, convertView, parent);
        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){
            layoutInflater = (LayoutInflater) contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.customlist,parent, false);
            viewHolder.user = convertView.findViewById(R.id.usernameTV);
            viewHolder.pass = convertView.findViewById(R.id.passwordTV);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.user.setText(userLst.get(position).get("UserName"));
        viewHolder.pass.setText(userLst.get(position).get("Password"));
        return convertView;
    }
    class ViewHolder{
        TextView user,pass;
    }
}
