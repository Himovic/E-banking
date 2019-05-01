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
 * Created by Hichem Himovic on 10/06/2017.
 */

public class ExchangeAdapter extends ArrayAdapter<ExchangeModel> {
    public ExchangeAdapter(Context context, ArrayList<ExchangeModel> SetofExchanges){
        super(context,0,SetofExchanges);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.exchangeadapter,parent,false);
        }
        ExchangeModel exchangeModel = getItem(position);
        TextView nameMoney = (TextView)convertView.findViewById(R.id.moneyName);
        TextView valueMoney = (TextView)convertView.findViewById(R.id.moneyValue);
        nameMoney.setText(exchangeModel.getRateName());
        valueMoney.setText(exchangeModel.getRateDouble()+"");
        return convertView;
    }
}
