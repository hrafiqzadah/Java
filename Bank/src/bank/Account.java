/* Hazrat Rafiqzadah
 * Bank Project
 * Account Class
*/

package bank;


public class Account {
    private int count;
    int ID;
    String owner;
    double balance;
    
    private Account(){
        
    }

    public int getID() {
        return ID;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public Account(int i) {
        count++;
        this.ID = count;
    }
    
    

    public Account(int ID, String owner, double balance) {
        this.ID = ID;
        this.owner = owner;
        this.balance = balance;
        this.count++;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void copy(Account copy){
        this.ID = copy.getID();
        this.balance = copy.getBalance();
        this.owner = copy.getOwner();
    }
    
    public Account clone(){
        Account clone = new Account();
        clone.ID = this.ID;
        clone.balance = this.balance;
        clone.owner = this.owner;
        
        return clone;
    }
    

    @Override
    public String toString() {
        String s ="";
        s= "\nID: "+ ID +
                "\nOwner: " + owner +
                "\nBalance: " + balance;
        return s;
    }
    
    public void Print(){
        System.out.println(this.toString());
    }
    
    
}
