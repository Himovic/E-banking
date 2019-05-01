package com.app.ebank.mbanking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hichem Himovic on 15/06/2017.
 */

public class ActuAdapter extends ArrayAdapter<ModelActu> {
    public ActuAdapter(Context context, ArrayList<ModelActu>SetOfActu){
        super(context,0,SetOfActu);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.actualityadapter,parent,false);
        }
        ModelActu ItemActu = getItem(position);
        TextView event = (TextView)convertView.findViewById(R.id.event);
        TextView date = (TextView)convertView.findViewById(R.id.date);
        TextView hour = (TextView)convertView.findViewById(R.id.hour);
        TextView place = (TextView)convertView.findViewById(R.id.place);
        event.setText(ItemActu.getEvent());
        date.setText(ItemActu.getDate());
        hour.setText(ItemActu.getHour());
        place.setText(ItemActu.getPlace());
        return convertView;
    }
}
