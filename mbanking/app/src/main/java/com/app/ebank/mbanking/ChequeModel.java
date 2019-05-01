package com.app.ebank.mbanking;

import java.util.Date;

/**
 * Created by Hichem Himovic on 08/06/2017.
 */

public class ChequeModel {
    private String NumCompte;
    private int NumCheque;
    private String Etat;
    private String Type;
    private int PagesRest;
    private String DateDem;
    private int idClient;
    public ChequeModel(){

    }
    public ChequeModel(int idClient,String NumCompte,String Type,int PagesRest,String DateDem){
        this.setIdClient(idClient);
        this.setNumCompte(NumCompte);
        this.setType(Type);
        this.setPagesRest(PagesRest);
        this.setDateDem(DateDem);
    }
    public ChequeModel(int idClient,String NumCompte,int NumCheque,String Etat,String DateDem){
        this.idClient=idClient;
        this.NumCompte=NumCompte;
        this.DateDem=DateDem;
        this.setNumCheque(NumCheque);
        this.setEtat(Etat);
    }

    public String getNumCompte() {
        return NumCompte;
    }

    public void setNumCompte(String numCompte) {
        NumCompte = numCompte;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getPagesRest() {
        return PagesRest;
    }

    public void setPagesRest(int pagesRest) {
        PagesRest = pagesRest;
    }

    public String getDateDem() {
        return DateDem;
    }

    public void setDateDem(String dateDem) {
        DateDem = dateDem;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getNumCheque() {
        return NumCheque;
    }

    public void setNumCheque(int numCheque) {
        NumCheque = numCheque;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }
}
