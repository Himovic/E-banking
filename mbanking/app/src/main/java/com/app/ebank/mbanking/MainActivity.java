package com.app.ebank.mbanking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText emailEdit;
    EditText passEdit;
    Button Authentification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new LoginFragment())
                .commit();

        /*emailEdit = (EditText)findViewById(R.id.emailedit);
        passEdit = (EditText)findViewById(R.id.passedit);
        Authentification = (Button)findViewById(R.id.login);
        Authentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EMAIL = emailEdit.getText().toString();
                String PASSWORD = passEdit.getText().toString();
                if(EMAIL.contains("@")&&(EMAIL.length()>5)){
                    if((PASSWORD.length()>=4)){
                        AsyncTaskLogin doneRequest = new AsyncTaskLogin();
                        doneRequest.execute(EMAIL,PASSWORD);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Le mot de passe est tros court").setTitle("Error");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Entrez un email valide").setTitle("Error");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });*/

    }


    /*private class AsyncTaskLogin extends AsyncTask<String,Void,ClientModel>{
        @Override
        protected ClientModel doInBackground(String... urls) {
            // Perform the HTTP request for earthquake data and process the response.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            String email = (String)urls[0];
            String password =(String)urls[1];
            Log.e("MainActivity","the email is :"+email);
            Log.e("MainActivity","the password is :"+password);
            //String URL="http://192.168.1.3/mbanking/login.php?Email=chabannehichem@gmail.com&%20Password=test";
            //String URL="http://192.168.1.3/mbanking/login.php?Email="+email+"&Password="+password;
            String URL=Server.SERVER+"/login.php?Email="+email+"&Password="+password;
            //String urlParams = "name="+email+"&password="+password;
            ClientModel client = QueryLogin.fetchClientData(URL);
            if(client == null){
                return client;
            }else {
                return client;
            }
        }
        ProgressDialog process = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            process.setTitle("Authentification");
            process.setMessage("Patientez s'il vous plait...");
            process.setCancelable(false);
            process.show();
        }

        @Override
        protected void onPostExecute(ClientModel clientModel) {
            if(clientModel == null){
                process.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Email ou mot de passe sont érronés").setTitle("Error");
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                process.dismiss();
                Intent intent = new Intent(MainActivity.this,ClientSpaceActivity.class);
                intent.putExtra("clientmodel",clientModel);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"BIENVENUE",Toast.LENGTH_LONG).show();
            }
        }
    }*/
}
