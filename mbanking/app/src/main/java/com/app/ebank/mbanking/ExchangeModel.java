package com.app.ebank.mbanking;

/**
 * Created by Hichem Himovic on 10/06/2017.
 */

public class ExchangeModel {
    private String base;
    private String date;
    private String rateName;
    private double rateDouble;
    public ExchangeModel(String base,String date,String rateName,double rateDouble){
        this.setBase(base);
        this.setDate(date);
        this.setRateName(rateName);
        this.setRateDouble(rateDouble);
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public double getRateDouble() {
        return rateDouble;
    }

    public void setRateDouble(double rateDouble) {
        this.rateDouble = rateDouble;
    }
}
