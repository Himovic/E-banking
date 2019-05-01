package com.app.ebank.mbanking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.RelativeDateTimeFormatter;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;
import java.util.Date;

public class ChequeActivity extends AppCompatActivity {
    EditText numerocompte,pagesrestante;
    RadioButton firstpage,secondpage;
    Button demander;
    int IDCLIENT=0;
    String Type="",NUMCOMPTE="";
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheque);
        numerocompte = (EditText)findViewById(R.id.numcompte);
        pagesrestante=(EditText)findViewById(R.id.pagerest);
        firstpage = (RadioButton)findViewById(R.id.pagefirst);
        secondpage = (RadioButton)findViewById(R.id.pagesecond);
        demander = (Button)findViewById(R.id.getcheque);
        Intent intent = getIntent();
        String NumCompte = intent.getStringExtra("numcompte");
        numerocompte.setText(NumCompte);
        numerocompte.setFocusable(false);
        demander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                IDCLIENT=intent.getIntExtra("idClient",0);
                String numeroCompte = numerocompte.getText().toString();
                if(numeroCompte.length()<=6){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChequeActivity.this);
                    builder.setMessage("Vérifier votre numéro de compte").setTitle("Error");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                String pagesRestante = pagesrestante.getText().toString();
                if(pagesRestante.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChequeActivity.this);
                    builder.setMessage("Un champ (page(s) réstante(s) manque").setTitle("Error");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                int pages_Restante = Integer.valueOf(pagesRestante);
                if((firstpage.isChecked()==false)&&(secondpage.isChecked()==false)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChequeActivity.this);
                    builder.setMessage("Veuillez séléctionner le type de chéque a entrer").setTitle("Error");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                if(firstpage.isChecked()){
                    Type="10 pages";
                }
                if(secondpage.isChecked()){
                    Type="20 pages";
                }
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                Date datea = new Date();
                String Date = sdf.format(datea);
                ChequeModel cheque = new ChequeModel(IDCLIENT,numeroCompte,Type,pages_Restante,Date);
                AsyncTaskCheque taskCheque = new AsyncTaskCheque();
                taskCheque.execute(cheque);
            }
        });
    }

    private class AsyncTaskCheque extends AsyncTask<ChequeModel,Void,Boolean>{
        @Override
        protected Boolean doInBackground(ChequeModel... params) {
            if(params[0] == null){
                return false;
            }
            int idClient =params[0].getIdClient();
            String numeroCompte = params[0].getNumCompte();
            int pages_restante = params[0].getPagesRest();
            String Type = params[0].getType();
            String date = params[0].getDateDem();
            Log.e("ChequeActivity","Attributs inside doInBackground");
            Log.e("NumeroCompte :",""+numeroCompte);
            Log.e("Type :",""+Type);
            Log.e("Page restante :",""+pages_restante);
            String IDCLIENT= String.valueOf(idClient);
            String URL_BASE="/addCheque.php?";
            String URL_IDCLIENT = "idClient="+idClient;
            String URL_Date ="&DateDem="+date;
            String URL_NUM_COMPTE="&NumCompte="+numeroCompte;
            String URL_PAGES_REST = "&PagesRest="+pages_restante;
            String URL_TYPE = "&Type="+Type;
            String URL1=Server.SERVER+URL_BASE+URL_IDCLIENT+URL_Date+URL_NUM_COMPTE+URL_PAGES_REST+URL_TYPE;
            //String URL=Server.SERVER+"/addCheque.php?idClient="+idClient+"&NumCompte="+numeroCompte+"&PagesRest="+pages_restante+"&Type="+Type+"&DateDem="+date;
            String URL = Server.SERVER+"/addCheque.php?DateDem="+date+"&NumCompte="+numeroCompte+"&PagesRest="+pages_restante+"&Type="+Type+"&idClient="+IDCLIENT;
            boolean finalResult = QueryCheque.AddNewCheque(URL1);
            return finalResult;
        }
        ProgressDialog process = new ProgressDialog(ChequeActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            process.setTitle("Demande en cours");
            process.setMessage("Patientez s'il vous plait...");
            process.setCancelable(false);
            process.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean == false){
                process.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(ChequeActivity.this);
                builder.setMessage("Votre demande n'a pas été envoyer").setTitle("Error");
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                process.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(ChequeActivity.this);
                builder.setMessage("Demande Envoyer").setTitle("Succées");
                AlertDialog dialog = builder.create();
                dialog.show();
                //Intent intent = new Intent(ChequeActivity.this,ClientSpaceActivity.class);
                //startActivity(intent);
            }
        }
    }

}
