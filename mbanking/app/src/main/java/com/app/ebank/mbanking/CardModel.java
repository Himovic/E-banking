package com.app.ebank.mbanking;

import java.util.Date;

/**
 * Created by Hichem Himovic on 08/06/2017.
 */

public class CardModel {
    private int ID_IMAGE;
    private String cardName;
    private double cardPrice;
    private String cardDescription;
    private String cardContrainte;
    private int idClient;
    private String typeCarte;
    private String date;
    public CardModel(int ID_IMAGE,String cardName,double cardPrice,String cardDescription,String cardContrainte){
        this.setID_IMAGE(ID_IMAGE);
        this.setCardName(cardName);
        this.setCardPrice(cardPrice);
        this.setCardDescription(cardDescription);
        this.setCardContrainte(cardContrainte);
    }
    public CardModel(int idClient, String date, String cardName){
        this.setIdClient(idClient);
        this.setDate(date);
        this.setCardName(cardName);
    }
    public int getID_IMAGE() {
        return ID_IMAGE;
    }

    public void setID_IMAGE(int ID_IMAGE) {
        this.ID_IMAGE = ID_IMAGE;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public double getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(double cardPrice) {
        this.cardPrice = cardPrice;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getCardContrainte() {
        return cardContrainte;
    }

    public void setCardContrainte(String cardContrainte) {
        this.cardContrainte = cardContrainte;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
