import java.io.IOException;
import java.util.Scanner;

public class Customer {
    private String firstName, lastName, username, password;
    public String id;
    private DataManager dataManager = new DataManager();
    Scanner scanner = new Scanner(System.in);

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getters and setters for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getters and setters for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getters and setters for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Password.hashPassword(password);
    }

    // Getters and setters for id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void register() throws IOException {
        try {

            System.out.println("Enter First Name:");
            firstName = scanner.nextLine();

            System.out.println("Enter Last Name:");
            lastName = scanner.nextLine();

            System.out.println("Enter your new username: ");
            username = scanner.nextLine();
            // Input prompts and user details gathering

            while (!dataManager.checkIfUsernameAvailable(username)) {
                System.out.println("This username is taken.");
                System.out.println("Enter your new username: ");
                username = scanner.nextLine();
            }

            System.out.println("Enter Password:");
            password = scanner.nextLine();
            String passwordToSave = Password.hashPassword(password);

            // Write user details using DataManager
            dataManager.saveUserData(firstName, lastName, username, passwordToSave);

            // Additional logic after registration
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(){
        try {
            System.out.println("Username \n");
            username = scanner.nextLine();
            System.out.println("Password: \n");
            password = scanner.nextLine();
            Object[] result = dataManager.authenticateUser(username, password);

            boolean authentication = (boolean) result[0];
            System.out.println(authentication);
            this.id = (String) result[1];

            if (authentication){
                Prompts.loginSuccess();
                Main.loginFlag = true;
            }
            else {
                System.out.println("Wrong Credentials");
                Main.loginFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

