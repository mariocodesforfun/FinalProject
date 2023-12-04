import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SavingAccount extends Account implements SavingsAccount {

    //constants
    private static final String CSV_FILENAME = "src/Accounts.csv";
    final private static double MIN_INTEREST_RATE = 1.5/100;
    private static final int TYPE = Enum.savingType();


    //member variables

    private double interestRate;
    String user_id;
    private int status;

    int years;


    Scanner scanner = new Scanner(System.in);



    //constructors
    //1 - default
    SavingAccount(){
    }
    //takes the user_id as the only parameter so the new object that is created belongs to a user.
    SavingAccount(String user_id){
        super();
        this.user_id = user_id;
        this.balance = 0;
        this.interestRate = MIN_INTEREST_RATE;
    }

    //2 - custom
    SavingAccount(double balance, double interestRate){
        super(balance);
        this.interestRate = interestRate;
    }

    //setters
    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    public void setAccNumber(long accNumber){
        this.accNumber = accNumber;
    }

    public void setType(int type){
        this.type = type;
    }


    public void setStatus(int status){
        this.status = status;
    }
    public void setSavingYears(int years){
        this.years = years;
    }

    //getters
    public double getInterest(){
        return this.interestRate;
    }
    public double getBalance(){
        return this.balance;
    }

    public int getStatus(){
        return this.status;
    }

    public int getSavingYears(){
        return this.years;
    }


    public double getInterestRateOnYear(int years){
        if (years==1){
            this.interestRate = 1.5;
        } else if (years==2) {
            interestRate = 3;
        }
        else if (years==3){
            interestRate = 9;
        }
        return interestRate;
    }

    public void createAccount(String id) throws IOException {
        this.user_id = id;
        Prompts.uniqueIdentifierInfo();
        System.out.println("Please enter the initial deposit amount. Required to create an account");
        balance = scanner.nextDouble();

        System.out.println("Please choose from our deals. Enter the number of years");
        System.out.println("1. 1 year with 1.5% interest");
        System.out.println("2. 3 years with 3% interest");
        System.out.println("3. 5 years with 9% interest. Beat the inflation with this deal!");
        years = scanner.nextInt();

        interestRate = getInterestRateOnYear(years);
        accNumber = Account.generateNumber();
        setStatus(Enum.activeStatus());

        FileWriter writer = new FileWriter(CSV_FILENAME, true);
        writer.write(accNumber + "," + balance + "," + user_id + "," + interestRate + "," + TYPE + "," + getStatus() + ","  + "\n");
        writer.close();
        Prompts.accountCreationSuccess();
    }



    //implementation of abstract methods
    //deposit money
    public void deposit(double amount){
        this.balance += amount;

    }


    //withdraw money
    public void withdraw(double amount) {
        this.balance -= amount;
    }

}
