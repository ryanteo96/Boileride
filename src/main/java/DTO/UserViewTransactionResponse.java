package DTO;

import java.util.ArrayList;

public class UserViewTransactionResponse {
    private int result;
    private ArrayList<DtoTransaction> transactionlist;

    public UserViewTransactionResponse(int result, ArrayList<DtoTransaction> transactionlist) {
        this.result = result;
        this.transactionlist = transactionlist;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<DtoTransaction> getTransactionlist() {
        return transactionlist;
    }

    @Anno(name="transactionlist")
    public void setTransactionlist(ArrayList<DtoTransaction> transactionlist) {
        this.transactionlist = transactionlist;
    }

    @Override
    public String toString() {
        return "UserViewTransactionResponse{" +
                "result=" + result +
                ", transactionlist=" + transactionlist +
                '}';
    }
}
