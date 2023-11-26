import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Customer {
    private static final String CSV_FILENAME = "src/customers.csv";
    Scanner scanner = new Scanner(System.in);
    String firstName, lastName, username, password, passwordToSave, id;





//check if id exists in the database
    private List<String> readExistingIds() {
        List<String> existingIds = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    existingIds.add(data[3]); //ID is at index 2
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return existingIds;
    }

    //generate a unique id for the new user - this will serve as a primary key to build relationship between Customers model and Accounts(model)
    private String generateUniqueRandomId(List<String> existingIds) {
        Random random = new Random();
        String randomId;
        do {
            randomId = String.format("%04d", random.nextInt(10000)); // Generating a 4-digit random ID
        } while (existingIds.contains(randomId));
        return randomId;
    }


    //register new users
    public void register() {
        try {
            System.out.println("Enter First Name:");
            firstName = scanner.nextLine();

            System.out.println("Enter Last Name:");
            lastName = scanner.nextLine();

            System.out.println("Enter you new username: ");
            username = scanner.nextLine();

            System.out.println("Enter Password:");
            password = scanner.nextLine();
            passwordToSave = Password.hashPassword(password);

            FileWriter writer = new FileWriter(CSV_FILENAME, true);
            List<String> existingIds = readExistingIds();

            writer.write(firstName.toLowerCase() + "," + lastName.toLowerCase() + "," + username + "," + generateUniqueRandomId(existingIds) + "," + passwordToSave + "," + Instant.now().toString() + "\n");

            writer.close ();

            Prompts.registerSuccess();
            login();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Object[] checkCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILENAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5 && data[2].equalsIgnoreCase(username) && Password.verifyPassword(password, data[4])) {
                    return new Object[]{true, data[3]};
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Object[]{false, null};
    }




    public void login(){
        System.out.println("Username \n");
        username = scanner.nextLine();
        System.out.println("Password: \n");
        password = scanner.nextLine();
        Object[] result = checkCredentials(username, password);

        boolean authentication = (boolean) result[0];
        this.id = (String) result[1];

        if (authentication){
            Prompts.loginSuccess();
            Main.loginFlag = true;
        }
        else {
            System.out.println("Wrong Credentials");
            Main.loginFlag = false;
        }
    }

}
