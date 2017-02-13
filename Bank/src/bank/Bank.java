/* Hazrat Rafiqzadah
 * Bank Project
 * Main Bank Class
*/
package bank;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
public class Bank {
    
    AccArray AccArr = new AccArray(50);
    TransArray TransArr = new TransArray(300);
    static Scanner keyboard = new Scanner(System.in);
    private static final double OverDraft_Fee = 35.0;
    private static final double Interest_Rate = 0.02;

    public static void main(String[] args) throws ParseException {
        
        Bank b = new Bank();
        int choice;
        do{
        choice = b.DisplayMenu();
        
        switch(choice){
            case 1:
                System.out.println("Reading And Sorting Accounts\n\n");
                b.AccArr.Read("accounts.txt");
                b.AccArr.Sort();
                b.TransArr.Read("trans.txt");
                b.TransArr.SortByDate();
                for(int i=0; i<b.TransArr.getCount(); i++){
                if(b.TransArr.getCurrentmaxtransID() < b.TransArr.transactionAt(i).getTransactionID())
                    b.TransArr.setCurrentmaxtransID(b.TransArr.transactionAt(i).getTransactionID());
                }
                b.TransArr.setCurrentmaxtransID(b.TransArr.getCurrentmaxtransID()+1);
                break;
                
            case 2:
                b.Reconcile();
                break;
            case 3:
                b.PrintAccByID();
                break;
            case 4:
                b.PrintAccByOwner();
                break;
            case 5:
                b.PrintAccSumm();
                break;
            case 6:
                System.out.println("Printing Detail Report");
                b.TransArr.SortByID();                
                b.DetailReport();
                break;
            case 7:
                System.out.println("Printing Accounts");
                b.PrintAllAcc();
                break;
            case 8:   
                b.PrintAllTrans();
                break;
            case 9:
                System.out.println("Saving Files....");
                b.TransArr.SortByID();
                b.SaveAccTrans();
                break;
                
        }
        }while(choice!=10);
        
        
    }

    public Bank() {
        AccArray AccArr = new AccArray(50);
        TransArray TransArr = new TransArray(300);    
    }
    
    
    
    public void DetailReport(){

        for(int i=0; i<AccArr.getCount(); i++){
            AccArr.accAt(i).Print();
            System.out.println("Tran ID \tDate \t\t\tType \t\tAmount");
            for(int j=0; j<TransArr.getCount(); j++){
                if(AccArr.accAt(i).getID() ==
                        TransArr.transactionAt(j).getAccountID())
                    System.out.println(TransArr.transactionAt(j).getTransactionID()
                    +"\t\t"+ TransArr.transactionAt(j).getDate()
                    //+"\t\t"+ TransArr.transactionAt(i).getDate()
                    +"\t\t"+ TransArr.transactionAt(j).getType()
                    +"\t\t"+ TransArr.transactionAt(j).getTransAmount());
                    
            }
        }
    }
    
    
        public int DisplayMenu(){
        int choice;
        System.out.println("Please Choose Number with Corresponding Menu: ");
        System.out.println("1-Read and sort account and transaction files");
        System.out.println("2-Reconcile accounts");
        System.out.println("3-Find account by ID-Binary Search-List Transactions");
        System.out.println("4-Find account by owner-Selection Search-List Transactions");
        System.out.println("5-Print account summary report");
        System.out.println("6-Print account detail report");
        System.out.println("7-Print all accounts");
        System.out.println("8-Print all transactions");
        System.out.println("9-Save account and transactions to file");
        System.out.println("10-Exit");
        System.out.println("Enter a choice:");
        do{
            choice = keyboard.nextInt();
        }while(choice <1 || choice>10);

        return choice;
    }
        
        // Reconcile
    public void Reconcile() throws ParseException{
        int acct=0, trans=0, count=0;
        double balance = 0.0;
        double interest = 0.0;
        Date date;
        
        System.out.println("Reconciling accounts ...");
        //TransArr.SortByDate();
        while(acct < AccArr.getCount()){
            balance = AccArr.accAt(acct).getBalance();
            System.out.println("acct = " + acct + " begin balance = " + balance);
            while(AccArr.accAt(acct).getID() == TransArr.transactionAt(trans).getAccountID()){
                if(TransArr.transactionAt(trans).getType() == 'd')
                    balance += TransArr.transactionAt(trans).getTransAmount();
                else if(TransArr.transactionAt(trans).getType() == 'w')
                    balance -= TransArr.transactionAt(trans).getTransAmount();
                
                //System.out.println("acct = " + acct + " Current balance = " + balance);
                if(balance < 0.0){
                    TransArr.transactionAt(trans).Print();
                    System.out.println("acct = " + acct + " Current balance = " + balance);
                    date = new Date();
                    TransArr.Insert(TransArr.getCurrentmaxtransID(),
                            AccArr.accAt(acct).getID(), date, 'o', OverDraft_Fee);
                    balance -= OverDraft_Fee;
                }
                trans++;
                count++;
                if(trans >= TransArr.getCount())
                    break;
            }
            if(count>0){
                if(balance > 0.0){
                    interest = Round(balance*(Interest_Rate/12.0), 2);
                    date = new Date();
                    TransArr.Insert(TransArr.getCurrentmaxtransID(),
                            AccArr.accAt(acct).getID(), date, 'i', interest);
                    AccArr.accAt(acct).setBalance(Round(balance + balance*(Interest_Rate/12.0), 2));
                }
                else
                    AccArr.accAt(acct).setBalance(Round(balance, 2));
            }
            acct++;
            count = 0;
        }
        TransArr.SortByDate();
    }
    
        // Round a double to digits places
    private double Round(double d, int digits){
        int n = (int)(d*Math.pow(10.0, digits+1));
        n += 5;
        n /= 10;
        d = (double)n;
        d /= Math.pow(10.0, digits);
        //System.out.println("n = " + n + " d = " + d);
        return d;
    }
    
    public void PrintAccByID(){
        int id;
        System.out.println("Please Enter Account ID:");
        do{
            id = keyboard.nextInt();
        }while(id<1 || id > 5);
        
        AccArr.accAt(id-1).Print();
        for(int i=0; i<TransArr.getCount(); i++){
            if(TransArr.transactionAt(i).getAccountID() ==
                    id-1)
                TransArr.transactionAt(i).Print();
        }
    }
    
    public void PrintAccByOwner(){
        System.out.println("Please Enter a Name: ");
        String name = keyboard.next();
        String temp = keyboard.nextLine();
        int accid = AccArr.SearchByOwner(name);
        TransArr.SortByID();
        for(int i=0; i<TransArr.getCount(); i++){
            if(TransArr.transactionAt(i).getAccountID()== AccArr.accAt(accid).getID())
                TransArr.transactionAt(i).Print();
        }
    }
    
    public void PrintAccSumm(){
   
        System.out.println("Acc ID \t\tOwner\t\tBalance");
        for(int i=0; i<AccArr.getCount(); i++){
            System.out.println(AccArr.accAt(i).getID()
                    +"\t\t"+ AccArr.accAt(i).getOwner()
                    +"\t\t"+ AccArr.accAt(i).getBalance());
        }
    }
    
    
    public void SaveAccTrans(){
        TransArr.SortByID();
        AccArr.Save("AcctsFinal.txt");
        TransArr.Save("TransFinal.txt");

    }
    
    public void PrintAllAcc(){
        System.out.println("Acc ID \t\tOwner\t\t");
        for(int i=0; i<AccArr.getCount(); i++){
            System.out.println(AccArr.accAt(i).getID()
                    +"\t\t"+ AccArr.accAt(i).getOwner()
                    +"\t\t"+ AccArr.accAt(i).getBalance());
        }
    }
    
    public void PrintAllTrans(){
        TransArr.SortByID();
        System.out.println("Tran ID \tAcc ID \t\tDate  \t\t\tType\t\tAmount");
        for(int i=0; i<TransArr.getCount(); i++){
            System.out.println(TransArr.transactionAt(i).getTransactionID()
                    +"\t\t"+ TransArr.transactionAt(i).getAccountID()
                    +"\t\t"+ TransArr.transactionAt(i).getDate()
                    +"\t\t"+ TransArr.transactionAt(i).getType()
                    +"\t\t"+ TransArr.transactionAt(i).getTransAmount());
                    
        }
    }

    
    
}

