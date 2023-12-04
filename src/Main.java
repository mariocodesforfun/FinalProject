import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static public boolean loginFlag = false;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome! Please choose from the following menu: ");
        System.out.println();
        System.out.println("1. Register");
        System.out.println("2. Login");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                Customer user = new Customer();
                user.register();
                break;
            case 2:
                Customer loginUser = new Customer();
                loginUser.login();
                if (loginFlag) {
                    Prompts.menuChoice();
                    int menuChoice = input.nextInt();
                    switch (menuChoice) {
                        case 1:
                            DataManager customerInfo = new DataManager();
                            Customer loggedInCustomer = customerInfo.getCustomerByID(Integer.parseInt(loginUser.id));
                            System.out.println(loggedInCustomer.getFirstName());

                            if (loggedInCustomer != null) {
                                customerInfo.displayCustomerInfo(loggedInCustomer);
                                customerInfo.getAccountsInfo("9270");
                            }
                            else {
                            }
                            break;
                        case 2:
                            SavingAccount savingAccount = new SavingAccount(loginUser.id);
                            try {
                                savingAccount.createAccount(loginUser.id);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 3:
                            CheckingAccount checkingAccount = new CheckingAccount(loginUser.id);
                            try {
                                checkingAccount.createAccount(loginUser.id);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 4:
                            Account.processDeposit();
                            break;
                        case 5:
                            Account.processWithdrawal();
                            break;
                            default:
                            // Handle other menu choices
                            break;
                    }
                }
                break;
            default:
                // Handle other main menu choices
                break;
        }


    }




}

