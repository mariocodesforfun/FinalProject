import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SavingAccount extends Account implements SavingsAccount {

    private static final String CSV_FILENAME = "src/Accounts.csv";
    int status;

    public static final int TYPE = 2;
    static final private double minInterestRate = 1.5/100;
    private double minBalance;
    private double interestRate;

    String user_id;;

    Scanner scanner = new Scanner(System.in);

    int years;

    //constructors
    //1 - default
    SavingAccount(String user_id){
        super();
        this.user_id = user_id;
        this.minBalance = 0;
        this.interestRate = minInterestRate;
    }

    //2 - custom
    SavingAccount(double balance, double minBalance, double interestRate){
        super(balance);
        this.minBalance = minBalance;
        this.interestRate = interestRate;
    }

    //setters
    public void setInterestRate(double InterestRate){
        this.interestRate = interestRate;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    //getters
    public double getInterest(){
        return this.interestRate;
    }
    public double getBalance(){
        return this.balance;
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

    //implementation of abstract methods
    //deposit money
    @Override
    public void deposit(double amount){
        this.balance += amount;

    }
    //withdraw money
    @Override
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    private long generateNumber(){
        accNumber = (int) (Math.random()*10000000)+10000000;
        return this.accNumber;
    }



    @Override
    public void createAccount(String id) throws IOException {

        this.user_id = id;
        System.out.println("Please enter the initial deposit amount. Required to create an account");
        balance = scanner.nextDouble();
        System.out.println("Please choose from our deals. Enter the number of years");
        System.out.println("1 year - 1.5% interest");
        System.out.println("3 year - 3% interest");
        System.out.println("5 years 9% interest. Beat the inflation with this deal!");
        years = scanner.nextInt();
        accNumber = generateNumber();
        interestRate = getInterestRateOnYear(years);
        status = 1;

        FileWriter writer = new FileWriter(CSV_FILENAME, true);

        writer.write(accNumber + "," + balance + "," + user_id + "," + interestRate + "," + type + "," + status + ","  + "\n");

        writer.close();

    }


}
