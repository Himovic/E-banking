package com.app.ebank.mbanking;

/**
 * Created by Hichem Himovic on 12/06/2017.
 */

public class ModelOperation {
    private double Solde;
    private double oldSolde;
    private String CodeOperation;
    private String NumCompte;
    private String DateOp;
    public ModelOperation(double Solde,double oldSolde,String CodeOperation,String NumCompte,String DateOp){
        this.setSolde(Solde);
        this.setOldSolde(oldSolde);
        this.setCodeOperation(CodeOperation);
        this.setNumCompte(NumCompte);
        this.setDateOp(DateOp);
    }

    public double getSolde() {
        return Solde;
    }

    public void setSolde(double solde) {
        Solde = solde;
    }

    public double getOldSolde() {
        return oldSolde;
    }

    public void setOldSolde(double oldSolde) {
        this.oldSolde = oldSolde;
    }

    public String getCodeOperation() {
        return CodeOperation;
    }

    public void setCodeOperation(String codeOperation) {
        CodeOperation = codeOperation;
    }

    public String getNumCompte() {
        return NumCompte;
    }

    public void setNumCompte(String numCompte) {
        NumCompte = numCompte;
    }

    public String getDateOp() {
        return DateOp;
    }

    public void setDateOp(String dateOp) {
        DateOp = dateOp;
    }
}
