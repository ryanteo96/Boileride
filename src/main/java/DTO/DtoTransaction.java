package DTO;

import java.util.Date;

public class DtoTransaction {
    private int transactionid;
    private int touserid;
    private String tousername;
    private int fromuserid;
    private String fromusername;
    private Date datentime;
    private int amount;
    private String description;

    public DtoTransaction() {
    }

    public DtoTransaction(int transactionid, int touserid, String tousername, int fromuserid, String fromusername, Date datentime, int amount, String description) {
        this.transactionid = transactionid;
        this.touserid = touserid;
        this.tousername = tousername;
        this.fromuserid = fromuserid;
        this.fromusername = fromusername;
        this.datentime = datentime;
        this.amount = amount;
        this.description = description;
    }

    public int getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public int getTouserid() {
        return touserid;
    }

    public void setTouserid(int touserid) {
        this.touserid = touserid;
    }

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername) {
        this.tousername = tousername;
    }

    public int getFromuserid() {
        return fromuserid;
    }

    public void setFromuserid(int fromuserid) {
        this.fromuserid = fromuserid;
    }

    public String getFromusername() {
        return fromusername;
    }

    public void setFromusername(String fromusername) {
        this.fromusername = fromusername;
    }

    public Date getDatentime() {
        return datentime;
    }

    public void setDatentime(Date datentime) {
        this.datentime = datentime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DtoTransaction{" +
                "transactionid=" + transactionid +
                ", touserid=" + touserid +
                ", tousername='" + tousername + '\'' +
                ", fromuserid=" + fromuserid +
                ", fromusername='" + fromusername + '\'' +
                ", datentime=" + datentime +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
