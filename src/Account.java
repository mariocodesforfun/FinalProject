import java.io.IOException;

public abstract class  Account{

    long accNumber;
    double balance;

    //1 for savings, 2 for checking
    int type;

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public abstract void createAccount(String id) throws IOException;

    private long generateNumber(){
        accNumber = (int) (Math.random()*10000000)+10000000;
        return this.accNumber;
    }


    //default constructor - new number and $0 starting balance
    Account(){
        this.balance = 0;
        this.accNumber = generateNumber();
    }

    //custom constructor for accounts with a starting balance other than 0
    Account(double balance){
        this.balance = balance;
    }

}
