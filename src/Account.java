import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class  Account {

    static long accNumber;
    double balance;

    //1 for savings, 2 for checking
    int type;

    public void setAccNumber(long accNo){
        this.accNumber = accNo;
    }

    public long getAccNumber(){
        return this.accNumber;
    }

    static public long generateNumber(){
        accNumber = (int) (Math.random()*10000000)+10000000;
        return accNumber;
    }



    //default constructor - new number and $0 starting balance
    Account(){
        super();
        this.balance = 0;
        this.accNumber = generateNumber();
    }


    //custom constructor for accounts with a starting balance other than 0
    Account(double balance){
        this.balance = balance;
    }


    public void deposit(double amount) {
        this.balance += amount;
        System.out.println("$" + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("$" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    Account(long accNo){
        this.accNumber = accNo;
        }

    double getAccCurrentBalance(long accNo) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Accounts.csv"))) {
            String line;
            while ((line = reader.readLine())!=null){
                String[] data = line.split(",");
                System.out.println(accNo);
                if (accNo == Long.parseLong(data[0])){
                    Account account = new Account(accNo);
                    account.type = Integer.parseInt(data[4]);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return this.balance;

}



    static public void processDeposit() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your account number:");
        Long accountNumber = input.nextLong();
        System.out.println("Enter the deposit amount:");
        Double amount = input.nextDouble();

        Account account = new Account(accountNumber);
        account.deposit(amount);
    }

    static public void processWithdrawal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your account number:");
        Long accountNumber = input.nextLong();
        System.out.println("Enter the withdrawal amount:");
        Double amount = input.nextDouble();

        Account account = new Account(accountNumber);
        account.withdraw(amount);
    }
}
