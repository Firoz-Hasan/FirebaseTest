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
import java.util.zip.Inflater;

/**
 * Created by FIROZ HASAN on 3/13/2018.
 */

public class LatLongListAdapter extends ArrayAdapter  {
    LayoutInflater inflater;
    Context contxt;
    ArrayList<LatLong> latlongList = new ArrayList<LatLong>();


    public LatLongListAdapter(@NonNull Context context, ArrayList<LatLong> latlongLst) {
        super(context, R.layout.latlonglist, latlongLst);
        this.contxt = context;
        this.latlongList = latlongLst;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){
            inflater = (LayoutInflater) contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.latlonglist,parent,false);
            viewHolder.latTV = convertView.findViewById(R.id.latitudeTV);
            viewHolder.longTV = convertView.findViewById(R.id.longitudeTV);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.latTV.setText(Integer.toString(latlongList.get(position).getLatitude()));
        viewHolder.longTV.setText(Integer.toString(latlongList.get(position).getLongitude()));

        return convertView;

    }

    class ViewHolder{
        TextView latTV, longTV;
    }
}
