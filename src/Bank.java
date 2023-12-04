import java.util.ArrayList;
import java.util.List;

abstract public   class Bank  {
    String code;
    String address;
    private ArrayList<SavingAccount> accounts;

    public abstract void  displayAllAccount();
}
