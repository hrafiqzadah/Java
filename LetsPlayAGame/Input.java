
package letsplayagame;


public class Input {
    private final int growth = 10;
    private int[] numSet = new int[growth];
    private String input;
    private String[] output;
    private int count;
    
	//Constructor for Class
    public Input(String input) {
        count++;
        this.input = input;
        split(input);
        
    }
    //Another Contructor
    private Input(){
        
    }
	//Getter for Growth
    public int getGrowth() {
        return growth;
    }
	//Getter for Count
    public int getCount() {
        return count;
    }
    //Resize Method to Resize Array
    public void resize(int[] x){
        System.out.println("Count in Resize" + count);
        
        int[] d = new int[x.length + growth];
        System.arraycopy(x, 0, d, 0, x.length);
        numSet = d;
    }
	//Parsing Input Method with TRY & CATCH
    private void parse(){
        for(int i=0; i<count; i++){
            try{
                if(numSet.length < count){
                    resize(numSet);
                }
                if(output != null)
                    numSet[i] = Integer.parseInt(output[i]);
            }catch(NumberFormatException ex){ 
                numSet[i] = -1;
            }
        }
    }
    //Splitting Input With A WhiteSpace
    private void split(String input){
        if(input.contains(" ")){
            this.output = input.split(" ");
        }
        if(output != null)
            count = output.length;
        else
            count = 1;
        parse();
    }
    //Checking to See if Values are Good(Greater Than 0)
    public boolean valueCheck(){
        boolean goodvalue = true;
        
        for(int i=0; i<numSet.length; i++){
            if(numSet[i] < 0){
                goodvalue = false;
            }
        }
        return goodvalue;
    }
    //Checking For Rule Increasing
    public boolean increasingCheck(){
        
        for(int i=1; i<count;i++){
            if(numSet[i] < numSet[i-1]){
                return false;
            }
        }
        return true;
    }
    //Checking for Rule Decreasing
    public boolean decreasingCheck(){
        for(int i=1; i<count;i++){
            if(numSet[i] > numSet[i-1]){
                return false;
            }
        }
        return true;
    }
    //Checking to See if Input is Meant to Quit Game
    public boolean sentinalCheck(){
        
        
        for(int i=0; i<numSet.length; i++){
            if(numSet[i] != 0){
                return false;
            }
        }
        return true;
    }
    
    
}
