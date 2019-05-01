package com.app.ebank.mbanking;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/**
 * Created by Hichem Himovic on 11/06/2017.
 */

public class ConvertFragment extends Fragment {
    Button convert;
    Spinner from,to;
    EditText Montant;
    TextView resultat;
    String RecoverMontant;
    String FROM="",TO="";
    String[]AllArrayExchanges = {"DZD","EUR","USD","PLN","GBP","RUB","CZK","JPY"};
    public void UpdateUI(double ValueCurrency){
        resultat.setText("Total :"+ValueCurrency);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.convertfragment,container,false);
        resultat = (TextView)rootView.findViewById(R.id.result);
        convert = (Button)rootView.findViewById(R.id.convert);
        from = (Spinner)rootView.findViewById(R.id.spinner3);
        to = (Spinner)rootView.findViewById(R.id.spinner4);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.exchangeArray));
        ArrayAdapter<String>adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.exchangeArray1));
        from.setAdapter(adapter);
        to.setAdapter(adapter1);
        convert = (Button)rootView.findViewById(R.id.convert);
        Montant = (EditText)rootView.findViewById(R.id.montant);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((from.getSelectedItemPosition()==0)||(to.getSelectedItemPosition()==0)||(Montant.getText().toString()=="")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Error").setMessage("Entrez une valeur exact");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    int SelectedFrom = from.getSelectedItemPosition();
                    int SelectedTo = to.getSelectedItemPosition();
                    FROM=AllArrayExchanges[SelectedFrom-1];
                    TO=AllArrayExchanges[SelectedTo-1];
                    RecoverMontant = Montant.getText().toString();
                    Log.e("ConvertFragment","FROM in ONCLICK :"+FROM);
                    Log.e("ConvertFragment","TO in ONCLICK :"+TO);
                    Log.e("ConvertFragment","Montant :"+RecoverMontant);
                    String URL_CONVERT="http://api.fixer.io/latest?base="+FROM;
                    AsyncTaskConvert asyncTaskConvert = new AsyncTaskConvert();
                    asyncTaskConvert.execute(URL_CONVERT,FROM,TO,RecoverMontant);
                }
            }
        });
        return rootView;
    }
    private class AsyncTaskConvert extends AsyncTask<String,Void,Double>{
        @Override
        protected Double doInBackground(String... params) {
            if((params.length == 0)||(params[0]==null)){
                return null;
            }
            String URL = params[0];
            String FROM = params[1];
            String TO = params[2];
            double Montant = Double.parseDouble(params[3]);
            Log.e("ConvertFragment","URL in AsyncTask est : "+URL);
            Log.e("ConvertFragment","FROM in AsyncTask :"+FROM);
            Log.e("ConvertFragment","TO in AsyncTask :"+TO);
            Log.e("ConvertFragment","MONTANT in AsyncTask :"+Montant);
            double Result = QueryConvert.FetchCurrencyData(Montant,URL,TO);
            return Result;
        }

        @Override
        protected void onPostExecute(Double aDouble) {
            super.onPostExecute(aDouble);
            UpdateUI(aDouble);
        }
    }
}
