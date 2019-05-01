package com.app.ebank.mbanking;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OperationActivity extends AppCompatActivity {
    int IDCLIENT=0;
    TextView NumCompte,Solde;
    ListView operation_list_view;
    OperationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        Intent intent = getIntent();
        IDCLIENT = intent.getIntExtra("idClient",0);
        NumCompte = (TextView)findViewById(R.id.numCompte);
        Solde = (TextView)findViewById(R.id.Solde);
        operation_list_view = (ListView)findViewById(R.id.operation_list_view);
        AsyncTaskOperations asyncTaskOperations = new AsyncTaskOperations();
        asyncTaskOperations.execute(IDCLIENT);
    }
    public void UpdateUI(ArrayList<ModelOperation> modelOperations){
        NumCompte.setText(modelOperations.get(0).getNumCompte());
        Solde.setText(modelOperations.get(0).getSolde()+" DA");
        adapter = new OperationAdapter(this,modelOperations);
        operation_list_view.setAdapter(adapter);
    }
    private class AsyncTaskOperations extends AsyncTask<Integer,Void,ArrayList<ModelOperation>>{
        @Override
        protected ArrayList<ModelOperation> doInBackground(Integer... params) {
            if(params.length ==0){
                return null;
            }
            ArrayList<ModelOperation> AllOperations = new ArrayList<ModelOperation>();
            int idClient=params[0];
            String URL=Server.SERVER+"/allOperation.php?idClient="+idClient;
            AllOperations = QueryOperation.fetchExchangeData(URL);
            return AllOperations;
        }

        @Override
        protected void onPostExecute(ArrayList<ModelOperation> modelOperations) {
            super.onPostExecute(modelOperations);
            UpdateUI(modelOperations);
        }
    }
}
