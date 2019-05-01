package com.app.ebank.mbanking;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class BlockChequeActivity extends AppCompatActivity {
    EditText numCompte,numCheque;
    Button confirmer;
    Date date;
    int IDCLIENT=0;
    String NumCheque="",NumCompte="",Etat="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_cheque);
        numCompte = (EditText)findViewById(R.id.numCompte);
        numCheque = (EditText)findViewById(R.id.numCheque);
        confirmer = (Button)findViewById(R.id.confirmer);
        Intent intent = getIntent();
        IDCLIENT = intent.getIntExtra("idClient",0);
        String NumCompteFinal = intent.getStringExtra("numcompte");
        numCompte.setText(NumCompteFinal);
        numCompte.setFocusable(false);
        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Etat="Bloquer";
                NumCheque = numCheque.getText().toString();
                int ChequeNumber=Integer.parseInt(NumCheque);
                NumCompte = numCompte.getText().toString();
                if(NumCheque.length()<=3){
                    AlertDialog.Builder builder = new AlertDialog.Builder(BlockChequeActivity.this);
                    builder.setMessage("Le numéro de chéque est tros court").setTitle("Error");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                if(NumCompte.length()<=6){
                    AlertDialog.Builder builder = new AlertDialog.Builder(BlockChequeActivity.this);
                    builder.setMessage("Entrez un numéro de compte valide").setTitle("Error");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                Date datea = new Date();
                String Date = sdf.format(datea);
                ChequeModel blockCheque = new ChequeModel(IDCLIENT,NumCompte,ChequeNumber,Etat,Date);
                AsyncTaskBlockCheque taskBlockCheque = new AsyncTaskBlockCheque();
                taskBlockCheque.execute(blockCheque);
            }
        });
    }
    private class AsyncTaskBlockCheque extends AsyncTask<ChequeModel,Void,Boolean>{
        @Override
        protected Boolean doInBackground(ChequeModel... params) {
            if(params[0]==null){
                return false;
            }
            int idClient = params[0].getIdClient();
            String NumCompte = params[0].getNumCompte();
            int NumCheque = params[0].getNumCheque();
            String Etat = params[0].getEtat();
            String date = params[0].getDateDem();
            Log.e("BlockChequeActivity","NumChéque = "+NumCheque);
            Log.e("BlockChequeActivity","Etat = "+Etat);
            Log.e("BlockChequeActivity","Date = "+date.toString());
            //String URL=Server.SERVER+"/blockCheque.php?idClient="+idClient+"&NumCompte="+NumCompte+"& DateDem="+date+"& NumCheque="+NumCheque;
            String URL = Server.SERVER+"/blockCheque.php?NumCheque="+NumCheque+"&NumCompte="+NumCompte+"&DateDem="+date+"&idClient="+idClient;
            boolean finalResult = QueryCheque.AddNewCheque(URL);
            return finalResult;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean == false){
                AlertDialog.Builder builder = new AlertDialog.Builder(BlockChequeActivity.this);
                builder.setMessage("Votre demande n'a pas été envoyer").setTitle("Error");
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(BlockChequeActivity.this);
                builder.setMessage("Demande Envoyer").setTitle("Succées");
                AlertDialog dialog = builder.create();
                dialog.show();
                //Intent intent = new Intent(ChequeActivity.this,ClientSpaceActivity.class);
                //startActivity(intent);
            }
        }
    }
}
