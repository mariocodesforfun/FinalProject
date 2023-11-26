public interface SavingsAccount {
    void deposit(double amount);
    void withdraw(double amount);


    //setters
    void setInterestRate(double InterestRate);
    void setBalance(double balance);


    //getters
    double getInterest();
    double getBalance();
}

