/* Hazrat Rafiqzadah
 * Bank Project
 * Transaction Array Class
*/
package bank;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
public class TransArray {
    
    private Transaction TransArray[];
    private int count;
    private int growth;
    private int currentmaxtransID=0;

    public int getCurrentmaxtransID() {
        return currentmaxtransID;
    }    

    public void setCurrentmaxtransID(int currentmaxtransID) {
        this.currentmaxtransID = currentmaxtransID;
    }

    public TransArray(int length) {
        TransArray = new Transaction[length];
        count = 0;
        growth = length;
    }
    
    public int getCount() {
        return count;
    }

    public int getGrowth() {
        return growth;
    }
    
    public int getLength(){
        return TransArray.length;
    }
    
    public Transaction transactionAt(int i){
        if(i<count){
            return TransArray[i];
        }
        return null;
    }    
    
    public void Resize(){
        Transaction array2[] = new Transaction[TransArray.length + growth];
        for(int i=0; i<count; i++)
            array2[i] = TransArray[i].clone();
        TransArray = array2;
        
    }
    
    public void Insert(int transID, int accID, String date,
            char type, double amount) throws ParseException{
        if(count>=TransArray.length)
            Resize();
        TransArray[count] = new Transaction(transID, accID, date, type, amount);
        count++;
    }
    
    public void Insert(int transID, int accID, Date date,
            char type, double amount) throws ParseException{
        if(count>=TransArray.length)
            Resize();
        TransArray[count] = new Transaction(transID, accID, date, type, amount);
        count++;
        currentmaxtransID ++;
    }
    
    public void Read(String FileName){
        Scanner inputStream;
        String s;
        String sa[];
        
        try{
            inputStream = new Scanner(new File(FileName));
            while(inputStream.hasNextLine()){
                s = inputStream.nextLine();
                sa = s.split(",");
                int transid = Integer.parseInt(sa[0]);
                int accid = Integer.parseInt(sa[1]);
                char type = sa[3].charAt(0);
                double tranamount = Double.parseDouble(sa[4]);
                Insert(transid, accid, sa[2], type, tranamount);
            }
            inputStream.close();
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
        
    }
    
    public void Save(String FileName){
        PrintWriter outputStream = null;
        
        try{
            outputStream = new PrintWriter(FileName);
            for(int i=0; i<count; i++){
                outputStream.printf("%s,%s,%s,%s,%s\r\n", 
                        TransArray[i].getTransactionID(), 
                        TransArray[i].getAccountID(),
                        TransArray[i].getDate(),
                        TransArray[i].getType(),
                        TransArray[i].getTransAmount());
            }
            outputStream.close();
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
    }
    
    public void Swap(int i, int j){
        Transaction temp = TransArray[i].clone();
        TransArray[i].copy(TransArray[j]);
        TransArray[j].copy(temp);    
    }
    
    public void SortByDate(){
        int i,j;
        for(i=0; i<count-1; i++){   
            for(j=count-1; j>i; j--)
                if(TransArray[j].CompareTo(TransArray[j-1]) < 0)
                    Swap(j,j-1);
        }
    }
    
    public void SortByID(){
        int i,j;
        for(i=0; i<count-1; i++){
            for(j=count-1; j>i; j--)
                if(TransArray[j].getTransactionID() < 
                        TransArray[j-1].getTransactionID())
                Swap(j,j-1);
        }
    }    
    
    public int SearchByID(int id) {
        
        int min=0;
        int max = TransArray.length-1;
        
        while(min<=max){
            int mid = min+(max-min) /2;
            if(id == TransArray[mid].getTransactionID())
                return mid;
            if(id < TransArray[mid].getTransactionID())
                max = mid-1;
            else
                min = mid +1;   
        }
        return -1;
    }
    
    public void Print(){
        for(int i=0; i<count; i++){
            TransArray[i].Print();
        }
    }

}
