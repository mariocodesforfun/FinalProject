import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CheckingAccount extends Account implements CheckingAccountInterface{
    private static final String CSV_FILENAME = "src/Accounts.csv";

    double minBalance;
    double spendingLimit;
    int status;
    public static final int TYPE = Enum.checkingType();
    String user_id;

    long AccNumber;

    //scanner instance used for any input case withing this class
    Scanner scanner = new Scanner(System.in);

    //Constructors
    //1 - default
    CheckingAccount(String user_id){
        super();
        this.user_id = user_id;
        this.minBalance = 0;
        spendingLimit = 800;
    }
    //2 - custom
    CheckingAccount(double balance, double minBalance, double spendingLimit){
        super(balance);
        this.minBalance = minBalance;
        this.spendingLimit = spendingLimit;
    }



    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccNumber(long accNumber){
        this.accNumber = accNumber;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    // Getters
    public double getBalance() {
        return this.balance;
    }

    public int getStatus() {
        return this.status;
    }
    public int getAccountType(){
        return this.type;
    }
    public long getAccNumber(){
        return this.AccNumber;
    }


    public void createAccount(String id) throws IOException {
        this.user_id = id;
        Prompts.uniqueIdentifierInfo();
        System.out.println("Please enter the initial deposit amount. Required to create an account");
        balance = scanner.nextDouble();
        accNumber = Account.generateNumber();
        status = Enum.checkingType();

        FileWriter writer = new FileWriter(CSV_FILENAME, true);
        writer.write(accNumber + "," + balance + "," + user_id + "," + 0 + "," + TYPE + "," + status + ","  + "\n");
        writer.close();
        Prompts.accountCreationSuccess();
    }


    public void deposit(double amount){
        balance += amount;
    }
    public void withdraw(double amount){
        setBalance(this.balance-amount);
    }


}
