import java.io.IOException;
import java.util.Scanner;
public class Main {
    static public boolean loginFlag=false;

    public static void main(String[] args) {


        Bank laGuardiaBank = new Bank("MAC190-OOP", "Long Island City, Queens");


//        System.out.println("Welcome! Please choose from the following menu.");
//
//
        Scanner input = new Scanner(System.in);
        int choice;


        System.out.println("Welcome! Please choose from the following menu: " );
        System.out.println();
        System.out.println("1. Register");
        System.out.println("2. Login");


        choice = input.nextInt();
        if (choice==1){
            Customer user = new Customer();
            user.register();
        } else if (choice==2) {
            Customer user = new Customer();
             user.login();
             if (loginFlag){
                 System.out.println("Please choose from the following menu: ");
                 System.out.println("1. Get your existing account information");
                 System.out.println("2. Create a Savings Account");
                 System.out.println("3. Create a Checking Account");
                 choice = input.nextInt();
                 if (choice==2){
                    SavingAccount account = new SavingAccount(user.id);
                     try {
                         account.createAccount(user.id);
                     } catch (IOException e) {
                         throw new RuntimeException(e);
                     }
                 }
             }
             else{

             }
        }


    }
}