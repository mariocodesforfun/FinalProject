public class Prompts {
    static public String chooseAccountType = "Please enter 1 to create a new Savings Account or enter 1 to create a Checking Account";
    static public String startingBalance = "Please enter your starting balance. You have to start with more than $1.";
    static public String accountSuccess = "You have successfully created you account!";
    static public void registerSuccess()
    { System.out.println("You have been registered successfully. Now you can login");}
    static public void loginSuccess(){
        System.out.println("Login successful");
    }

    static public void accountCreationSuccess(){System.out.println("Account created successfully");}

    static public void menuChoice(){
        System.out.println("Please choose from the following menu: ");
        System.out.println("1. Get your existing account information");
        System.out.println("2. Create a Savings Account");
        System.out.println("3. Create a Checking Account");
        System.out.println("4. Deposit");
        System.out.println("5. Withdraw");
    }


    static public void uniqueIdentifierInfo(){
        System.out.println("Wee are going to create and id and account number for you");
    }




}
