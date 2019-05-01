package com.app.ebank.mbanking;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hichem Himovic on 10/06/2017.
 */

public class ExchangeFragment extends Fragment {
    private static final String EXCHANGE_URL ="http://api.fixer.io/latest?base=EUR";
    ListView listView;
    TextView base,LastDate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.exchangefragment,container,false);
        listView = (ListView)rootView.findViewById(R.id.list_view_exchange);
        base =(TextView)rootView.findViewById(R.id.base);
        LastDate = (TextView)rootView.findViewById(R.id.lastdate);
        AsyncTaskExchange asyncTaskExchange = new AsyncTaskExchange();
        asyncTaskExchange.execute(EXCHANGE_URL);
        return rootView;
    }
    public void UpdateUI(ArrayList<ExchangeModel> AllExchanges){
        base.setText(AllExchanges.get(0).getBase());
        LastDate.setText(AllExchanges.get(0).getDate());
        ExchangeAdapter adapter = new ExchangeAdapter(getActivity(),AllExchanges);
        listView.setAdapter(adapter);
    }
    private class AsyncTaskExchange extends AsyncTask<String,Void,ArrayList<ExchangeModel>>{
        @Override
        protected ArrayList<ExchangeModel> doInBackground(String... params) {
            if (params.length < 1 || params[0] == null) {
                return null;
            }
            ArrayList<ExchangeModel> ListofExchanges = QueryExchange.fetchExchangeData(params[0]);
            return ListofExchanges;
        }

        @Override
        protected void onPostExecute(ArrayList<ExchangeModel> exchangeModels) {
            super.onPostExecute(exchangeModels);
            UpdateUI(exchangeModels);
        }
    }
}
