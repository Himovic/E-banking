package com.app.ebank.mbanking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hichem Himovic on 10/06/2017.
 */

public class LoginFragment extends Fragment {
    EditText emailEdit;
    EditText passEdit;
    Button Authentification;
    public static int NUMBER_CLICK=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main,container,false);
        emailEdit = (EditText)rootView.findViewById(R.id.emailedit);
        passEdit = (EditText)rootView.findViewById(R.id.passedit);
        Authentification = (Button)rootView.findViewById(R.id.login);
        if(NUMBER_CLICK == 3){
            Authentification.setClickable(false);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Vous avez éssayer d'entrer 3 fois -Essayer a nouveau-").setTitle("TENTATIVE MULTIPLE")
                    .setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            emailEdit.setText("");
            passEdit.setText("");
        }
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Le mot de passe est tros court").setTitle("Error");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Entrez un email valide").setTitle("Error");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
        return rootView;
    }
    public static void getNumberInstance(){
        NUMBER_CLICK++;
    }
    private class AsyncTaskLogin extends AsyncTask<String,Void,ClientModel> {
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
        ProgressDialog process = new ProgressDialog(getActivity());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Email ou mot de passe sont érronés").setTitle("Error");
                AlertDialog dialog = builder.create();
                dialog.show();
                getNumberInstance();
            }else{
                process.dismiss();
                Intent intent = new Intent(getActivity(),ClientSpaceActivity.class);
                intent.putExtra("clientmodel",clientModel);
                startActivity(intent);
                Toast.makeText(getActivity(),"BIENVENUE",Toast.LENGTH_LONG).show();
                emailEdit.setText("");
                passEdit.setText("");
            }
        }
    }
}
