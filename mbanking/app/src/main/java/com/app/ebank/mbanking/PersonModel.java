package com.app.ebank.mbanking;

import java.util.Date;

/**
 * Created by Hichem Himovic on 07/06/2017.
 */

public class PersonModel {
    private String nom;
    private String prenom;
    private String adresse;
    private String type;
    private String email;
    private String password;
    private Date date;
    public PersonModel(){

    }
    public PersonModel(String nom,String prenom,String adresse,String type,String email,String password,Date date){
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setAdresse(adresse);
        this.setType(type);
        this.setEmail(email);
        this.setPassword(password);
        this.setDate(date);
    }
    public PersonModel(String nom,String prenom,Date date,String email){
        this.nom=nom;
        this.prenom=prenom;
        this.date=date;
        this.email=email;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
