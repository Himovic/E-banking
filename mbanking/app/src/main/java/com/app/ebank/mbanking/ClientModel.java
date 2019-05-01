package com.app.ebank.mbanking;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Hichem Himovic on 07/06/2017.
 */

public class ClientModel implements Serializable {
    private double solde;
    private String nom;
    private String prenom;
    private String email;
    private Date date;
    private String adresse;
    private String NumCompte;
    private Date dateen;
    private int idClient;
    public ClientModel(){

    }
    public ClientModel(int idClient,String nom,String prenom,Date date,String email,Date dateen,double solde,String adresse,String NumCompte){
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDate(date);
        this.setEmail(email);
        this.dateen=dateen;
        this.dateen=dateen;
        this.solde=solde;
        this.setNumCompte(NumCompte);
        this.setAdresse(adresse);
        this.setIdClient(idClient);
    }
    public Date getDateen() {
        return dateen;
    }

    public void setDateen(Date dateen) {
        this.dateen = dateen;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNumCompte() {
        return NumCompte;
    }

    public void setNumCompte(String numCompte) {
        NumCompte = numCompte;
    }
}
