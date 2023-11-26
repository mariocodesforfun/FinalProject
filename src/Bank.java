import java.util.ArrayList;
import java.util.List;

public class Bank {
    String code;
    String address;
    private ArrayList<SavingAccount> accounts;

    public Bank(String code, String address) {
        this.code = code;
        this.address = address;
        this.accounts = new ArrayList<>();
    }


}
