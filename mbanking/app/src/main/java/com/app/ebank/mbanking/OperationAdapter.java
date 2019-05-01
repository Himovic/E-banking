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
 * Created by Hichem Himovic on 12/06/2017.
 */

public class OperationAdapter extends ArrayAdapter<ModelOperation> {
    public OperationAdapter(Context context, ArrayList<ModelOperation>SetOfOperations){
        super(context,0,SetOfOperations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.operationadapter,parent,false);
        }
        ModelOperation modelOperation = getItem(position);
        TextView operation = (TextView)convertView.findViewById(R.id.opration);
        TextView oldSolde = (TextView)convertView.findViewById(R.id.oldSolde);
        TextView date = (TextView)convertView.findViewById(R.id.date);
        operation.setText(modelOperation.getCodeOperation());
        oldSolde.setText(modelOperation.getOldSolde()+" DA");
        date.setText(modelOperation.getDateOp());
        return convertView;
    }
}
