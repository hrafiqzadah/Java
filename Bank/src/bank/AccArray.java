/* Hazrat Rafiqzadah
 * Bank Project
 * Account Array Class
*/
package bank;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
public class AccArray {
    
    private Account Array[];
    private int count;
    private int growth;

    public AccArray(int length) {
        Array = new Account[length];
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
        return Array.length;
    }
    
    public Account accAt(int i){
        if(i<count){
            return Array[i];
        }
        return null;
    }
    
    public void Resize(){
        Account array2[] = new Account[Array.length + growth];
        for(int i=0; i<count; i++)
            array2[i] = Array[i].clone();
        Array = array2;
        
    }
    
    public void Insert(int ID, String Owner, double Balance){
        if(count>=Array.length)
            Resize();
        Array[count] = new Account(ID, Owner, Balance);
        count++;
    }
    
    public void Read(String fileName){
        Scanner inputStream;
        String s;
        String sa[];
        try{
            inputStream = new Scanner(new File(fileName));
            while(inputStream.hasNextLine()){
                s = inputStream.nextLine();
                sa = s.split(",");
                int id = Integer.parseInt(sa[0]);
                double balance = Double.parseDouble(sa[2]);
                Insert(id, sa[1], balance);
            }
            inputStream.close();
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
    }
    
    public void Save(String Filename){
        PrintWriter outputStream = null;
        try{
            outputStream = new PrintWriter(Filename);
            for(int i=0; i<count; i++){
                        outputStream.printf("%s,%s,%s\r\n", Array[i].getID(),
                        Array[i].getOwner(), Array[i].getBalance());
               
            }
            outputStream.close();
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
    }
    
    public void Sort(){
        
        for(int i=0; i<count; i++){
            for(int j=0; j<count-1; j++){
                if(Array[i].getID() < Array[j].getID())
                    Swap(j,i);
            }       
        }
            
        
    }
    
    public void Swap(int i, int j){
        Account temp = Array[i].clone();
        Array[i].copy(Array[j]);
        Array[j].copy(temp);    
    }
    
    public int SearchByID( int id) {
        
        int min=0;
        int max = Array.length-1;
        
        while(min<=max){
            int mid = min+(max-min) /2;
            if(id == Array[mid].getID())
                return mid;
            if(id < Array[mid].getID())
                max = mid-1;
            else
                min = mid +1;   
        }
        return -1;
    }
    
    public int SearchByOwner(String name){
        int count =0;
        for(int i=0; i<Array.length; i++){
            if(name.equalsIgnoreCase(Array[i].getOwner())){ 
                System.out.println("Found Match");
                count = i;
                break;
            }
            }
        System.out.println("" + count);
        return count;
    }
    
    public void Print(){
        for(int i=0; i<count; i++){
            Array[i].Print();
        }
    }
}


