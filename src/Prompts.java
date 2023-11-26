import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Prompts {
    static public String chooseAccountType = "Please enter 1 to create a new Savings Account or enter 1 to create a Checking Account";
    static public String startingBalance = "Please enter your starting balance. You have to start with more than $1.";
    static public String accountSuccess = "You have successfully created you account!";
    static public void registerSuccess()
    { System.out.println("You have been registered successfully. Now you can login");}
    static public void loginSuccess(){
        System.out.println("Login successful");
    }

}
