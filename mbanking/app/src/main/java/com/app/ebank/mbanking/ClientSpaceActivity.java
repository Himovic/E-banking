package com.app.ebank.mbanking;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ClientSpaceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView nom,daten,adresse,solde;
    int IDCLIENT=0;
    String NUM_COMPTE="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_space);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ClientModel client = (ClientModel)getIntent().getSerializableExtra("clientmodel") ;
        String nom=client.getNom();
        String adresse = client.getAdresse();
        String solde = client.getSolde()+"";
        Log.e("ClientSpaceAcitivity","name is :"+nom+" Address is :"+adresse+" Solde is :"+solde);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        IDCLIENT = client.getIdClient();
        AsyncTaskClientInfo taskClientInfo = new AsyncTaskClientInfo();
        taskClientInfo.execute(IDCLIENT);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.client_space, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(ClientSpaceActivity.this,LaunchActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    public void UpdateUI(ClientModel client){
        nom = (TextView)findViewById(R.id.name);
        daten = (TextView)findViewById(R.id.daten);
        adresse = (TextView)findViewById(R.id.adresse);
        solde = (TextView)findViewById(R.id.currentsolde);
        nom.setText(client.getNom()+" "+client.getPrenom());
        daten.setText(client.getDate()+"");
        adresse.setText(client.getAdresse());
        solde.setText(client.getSolde()+"");
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(ClientSpaceActivity.this,BlockChequeActivity.class);
            intent.putExtra("idClient",IDCLIENT);
            intent.putExtra("numcompte",NUM_COMPTE);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ClientSpaceActivity.this,ChequeActivity.class);
            intent.putExtra("idClient",IDCLIENT);
            intent.putExtra("numcompte",NUM_COMPTE);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ClientSpaceActivity.this,CardActivity.class);
            intent.putExtra("idClient",IDCLIENT);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(ClientSpaceActivity.this,OperationActivity.class);
            intent.putExtra("idClient",IDCLIENT);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(ClientSpaceActivity.this,ServicesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(ClientSpaceActivity.this,ContactActivity.class);
            intent.putExtra("idClient",IDCLIENT);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private class AsyncTaskClientInfo extends AsyncTask<Integer,Void,ClientModel>{
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
            NUM_COMPTE = clientModel.getNumCompte();
        }
    }
}
