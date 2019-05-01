package com.app.ebank.mbanking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hichem Himovic on 08/06/2017.
 */

public class CardAdapter extends ArrayAdapter<CardModel> {
    public CardAdapter(Context context, ArrayList<CardModel> SetofCards){
        super(context,0,SetofCards);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cardadapter,parent,false);
        }
        CardModel cardItem = getItem(position);
        TextView name = (TextView)convertView.findViewById(R.id.cardname);
        TextView price = (TextView)convertView.findViewById(R.id.cardprice);
        TextView contrainte = (TextView)convertView.findViewById(R.id.contrainte);
        TextView description = (TextView)convertView.findViewById(R.id.description);
        ImageView picCard = (ImageView)convertView.findViewById(R.id.cardpic);
        name.setText(cardItem.getCardName());
        price.setText(cardItem.getCardPrice()+" DA");
        contrainte.setText(cardItem.getCardContrainte());
        description.setText(cardItem.getCardDescription());
        picCard.setImageResource(cardItem.getID_IMAGE());
        return convertView;
    }
}
