package com.app.ebank.mbanking;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactActivity extends AppCompatActivity {
    EditText subject,message;
    Button send;
    int IDCLIENT=0;
    String Nom="",Prenom="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        subject = (EditText)findViewById(R.id.subject);
        message = (EditText)findViewById(R.id.message);
        send = (Button)findViewById(R.id.send);
        Intent intent = getIntent();
        IDCLIENT = intent.getIntExtra("idClient",0);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SUBJECT = subject.getText().toString();
                String MESSAGE = message.getText().toString();
                if((SUBJECT.length() >=4)){
                    if(MESSAGE.length() >= 8){
                        AsyncTaskClientInfo asyncTaskClientInfo = new AsyncTaskClientInfo();
                        asyncTaskClientInfo.execute(IDCLIENT);
                        Intent sendMail = new Intent(Intent.ACTION_SEND);
                        sendMail.setData(Uri.parse("mailto"));
                        sendMail.setType("text/plain");
                        sendMail.putExtra(Intent.EXTRA_EMAIL,"hichemhimovic@outlook.fr");
                        sendMail.putExtra(Intent.EXTRA_CC,"chabannehichem@gmail.com");
                        sendMail.putExtra(Intent.EXTRA_SUBJECT,SUBJECT);
                        sendMail.putExtra(Intent.EXTRA_TEXT,MESSAGE);
                        startActivity(Intent.createChooser(sendMail,"Envoi d'email..."));
                        finish();
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                        builder.setMessage("Longueur méssage inssufisante").setTitle("Error");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                    builder.setMessage("Longueur sujet inssufisante").setTitle("Error");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }
    private void UpdateUI(ClientModel clientModel){
        Nom = clientModel.getNom();
        Prenom = clientModel.getPrenom();
    }
    private class AsyncTaskClientInfo extends AsyncTask<Integer,Void,ClientModel> {
        @Override
        protected ClientModel doInBackground(Integer... params) {
            if((params[0]==null)||(params.length<1)) {
                return null;
            }
            int idClient = params[0];
            String URL=Server.SERVER+"/getClient.php?idClient="+idClient;
            ClientModel client = QueryLogin.fetchClientData(URL);
            if(client.getNom()==""){
                return null;
            }else {
                return client;
            }
        }

        @Override
        protected void onPostExecute(ClientModel clientModel) {
            super.onPostExecute(clientModel);
            UpdateUI(clientModel);
        }
    }
}
