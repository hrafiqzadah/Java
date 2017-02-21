/*
 * Hazrat Rafiqzadah
 * Andrew Rosen
 * Lab 1 "Lets Play a Game" Part 1
 */
package letsplayagame;
import java.util.Scanner;


public class LetsPlayaGame {


public static void main(String[] args) {
    
    //Declaration of Variables
    Scanner keyboard = new Scanner(System.in);
    int num;
    String sentinal = "notdone";
    
    //Game Instructions
    System.out.println("Welcome To 'Rule Guessing' Game!");
    System.out.println("This Game is Designed with a Set of 3 Rules");
    System.out.println("Your Objective is to Enter 3 numbers");
    System.out.println("In Such a Manner That You Guess The 'Rules' With Your Input");

    System.out.println("Enter 0 to Quit Game.");
    
    //Start of Game
    while(!sentinal.contains("answer")){
        String user = keyboard.nextLine();
        if(!user.contains("answer")){
            Input s = new Input(user);
            if(s.getCount() == 3){
                if(s.valueCheck()){
                    if(s.increasingCheck())
                        System.out.println("Increasing Rule Found!");
                    else if(s.decreasingCheck())
                        System.out.println("Decreasing Rule Found!");
                    else
                        System.out.println("No Rule Found. Try Again");
                }
                else{
                    System.out.println("You Entered a Bad Value. Try Again.");}
            }
            else{
                System.out.println("Please Enter 3 and ONLY 3 Numbers");
            }
        }else
            sentinal = user;
    }
    
}
}






