package com.app.ebank.mbanking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CardActivity extends AppCompatActivity {
    String GoldMCDes="Cette carte vous permet d'acheter des produits sur internet,réserver des hotels ou des billets d'avions." +
            "Vous pouvez aussi la transportée partout dans le monde et de retirer vos argents n'importe ou.";
    String GoldMCCon="Tous type de transaction";
    String SilverMCDes="Cette carte vous permet de réserver ou acheter des produits sur internet sachant que vous dépasser pas 80000.00DA";
    String SilverMCCon="Réservation d'internet seulement";
    int IDCLIENT=0;
    String TypeCarte="";
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Intent intent = getIntent();
        IDCLIENT = intent.getIntExtra("idClient",0);
        final ArrayList<CardModel>SetOfModels = new ArrayList<>();
        SetOfModels.add(new CardModel(R.drawable.mastercardgold,"Mastercard-Gold",50000.00,GoldMCDes,GoldMCCon));
        SetOfModels.add(new CardModel(R.drawable.mastercardsilver,"Mastercard-Silver",25000.00,SilverMCDes,SilverMCCon));
        SetOfModels.add(new CardModel(R.drawable.visagold,"Visa-Gold",75000.00,GoldMCDes,GoldMCCon));
        SetOfModels.add(new CardModel(R.drawable.visasilver,"Visa-Silver",35000.00,SilverMCDes,SilverMCCon));
        CardAdapter adapter = new CardAdapter(this,SetOfModels);
        final ListView AllCards = (ListView)findViewById(R.id.list_view_cards);
        AllCards.setAdapter(adapter);
        date = Calendar.getInstance().getTime();
        AllCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder confirme = new AlertDialog.Builder(CardActivity.this);
                confirme.setTitle("Confirmation").setMessage("Demande Envoyé , Vous serez dans quele-que jours");
                AlertDialog.Builder builder = new AlertDialog.Builder(CardActivity.this);
                builder.setMessage("Vous voulez demander une carte ?").setTitle("Demande Carte")
                        .setCancelable(false)
                        .setPositiveButton("Oui",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                Date datea = new Date();
                                String Date = sdf.format(datea);
                                TypeCarte=SetOfModels.get(position).getCardName();
                                Log.e("CardActivity","Type in onClick is :"+TypeCarte);
                                CardModel cardModel = new CardModel(IDCLIENT,Date,TypeCarte);
                                AsyncTaskAddCard taskAddCard = new AsyncTaskAddCard();
                                taskAddCard.execute(cardModel);
                                AlertDialog alertDialog = confirme.create();
                                alertDialog.show();
                            }
                        })
                        .setNegativeButton("Non",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private class AsyncTaskAddCard extends AsyncTask<CardModel,Void,Boolean>{
        @Override
        protected Boolean doInBackground(CardModel... params) {
            if(params[0] == null){
                return false;
            }
            int idClient = params[0].getIdClient();
            String date = params[0].getDate();
            String TypeCarte = params[0].getCardName();
            Log.e("CardActivity",date.toString());
            Log.e("CardActivity","Type in AsyncTask is :"+TypeCarte);
            String URLCLIENT = "idClient="+idClient;
            String URLDATE = "&Date="+date;
            String URLCARTE = "&NameCarte="+TypeCarte;
            String PHPSCRIPT = "/addCard.php?";
            //String URL=Server.SERVER+"/addCard.php?idClient="+idClient+"&Date="+date+"&NameCarte="+TypeCarte;
            String URL= Server.SERVER+PHPSCRIPT+URLCLIENT+URLDATE+URLCARTE;
            boolean finalResult = QueryCheque.AddNewCheque(URL);
            return finalResult;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean == false){
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Votre demande n'a pas été envoyer").setTitle("Error");
                AlertDialog dialog = builder.create();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Demande Envoyer").setTitle("Succées");
                AlertDialog dialog = builder.create();
                //Intent intent = new Intent(ChequeActivity.this,ClientSpaceActivity.class);
                //startActivity(intent);
            }
        }
    }
}
