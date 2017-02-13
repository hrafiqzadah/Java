/* Hazrat Rafiqzadah
 * Bank Project
 * Transaction Class
*/
package bank;

import java.text.ParseException;
import java.util.Scanner;
import java.util.Date;

public class Transaction {
    
    private static int count;
    private int TransactionID;
    private int AccountID;
    private Date date;
    char type;
    double TransAmount;


    
    
    private Transaction(){
        
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public double getTransAmount() {
        return TransAmount;
    }

    public char getType() {
        return type;
    }
    
    public String getDate(){
        String s = "";
        s+=( date.getMonth()+1 ) + "/";
        s+=(date.getDate()) + "/";
        s+=(date.getYear() +1900);
        return s;
    }

    public void setTransactionID(int TransactionID) {
        this.TransactionID = TransactionID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setTransAmount(double TransAmount) {
        this.TransAmount = TransAmount;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Transaction(int TransactionID, int AccountID, Date date, char type, double TransAmount) {
        this.TransactionID = TransactionID;
        this.AccountID = AccountID;
        this.date = date;
        this.type = type;
        this.TransAmount = TransAmount;
        count++;
    }

    public Transaction(int TransactionID, int AccountID, String date, 
            char type, double TransAmount) throws ParseException {
        this.TransactionID = TransactionID;
        this.AccountID = AccountID;
        this.date = new Date(date);
        this.type = type;
        this.TransAmount = TransAmount;
        count++;
    }
    
    public Transaction clone(){
        Transaction clone = new Transaction();
        clone.TransactionID = this.TransactionID;
        clone.AccountID = this.AccountID;
        clone.date = this.date;
        clone.type = this.type;
        clone.TransAmount = this.TransAmount;
        
        return clone;
    }
    
    public void copy(Transaction copy){
        this.TransactionID = copy.getTransactionID();
        this.AccountID = copy.getAccountID();
        this.date = new Date(copy.getDate());
        this.type = copy.getType();
        this.TransAmount = copy.getTransAmount();
    }
    
    public int CompareTo(Transaction b){        
        if(AccountID < b.getAccountID()){
            return -1;}
        else if(AccountID > b.getAccountID())
            return 1;
        else
            return date.compareTo(b.date);       
        
    }

    @Override
    public String toString() {
        String s="";
        s = "Transaction ID: " + TransactionID +
                "\tAccount: " + AccountID +
                "\nDate: " + date +
                "\nType: " + type +
                "\t\tTransaction Amount: " + TransAmount;
        return s;
    }
    
    public void Print(){
        System.out.println(this.toString());
    }
    
}
