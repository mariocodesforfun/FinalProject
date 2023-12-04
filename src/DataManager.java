import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataManager {
    private static final String CSV_FILENAME = "src/customers.csv";
    private static final String ACCOUNTS_CSV_FILENAME = "src/Accounts.csv";
    public boolean checkIfUsernameAvailable(String newUsername) throws IOException {
        // Logic to check if username is available in the data file
        // Return true if username is available, false otherwise
        BufferedReader reader = new BufferedReader(new FileReader(CSV_FILENAME));
        String line;
        while ((line = reader.readLine())!=null) {
            String[] userDetails = line.split(",");
            if (userDetails.length > 2) {
                String username = userDetails[2];
                if (username.equals(newUsername)){
                    reader.close();
                return false;}
                }
            }
        reader.close();
        return true;
    }



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

        private String generateUniqueRandomId(List<String> existingIds) {
        Random random = new Random();
        String randomId;
        do {
            randomId = String.format("%04d", random.nextInt(10000)); // Generating a 4-digit random ID
        } while (existingIds.contains(randomId));
        return randomId;
    }

    public void saveUserData(String firstName, String lastName, String username, String password) throws IOException {
        // Logic to save user data to the CSV file
            FileWriter writer = new FileWriter(CSV_FILENAME, true);
            List<String> existingIds = readExistingIds();

            writer.write(firstName.toLowerCase() + "," + lastName.toLowerCase() + "," + username + "," + generateUniqueRandomId(existingIds) + "," + password + "," + Instant.now().toString() + "\n");
            writer.close();
    }


    //
    public Object[] authenticateUser(String username, String password) {
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


    //get customer info based on id:

    public Customer getCustomerByID(int targetID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    int customerIdFromFile;
                    try {
                        customerIdFromFile = Integer.parseInt(data[3]); // Assuming ID is the first field
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    if (customerIdFromFile == targetID) {
                        // Create a Customer object using setters
                        Customer customer = new Customer();
                        customer.setFirstName(data[0]);
                        customer.setLastName(data[1]);
                        customer.setUsername(data[2]);
                        customer.setId(data[3]);
                        return customer;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //display customer info
    public void displayCustomerInfo(Customer customer) {
        if (customer != null) {
            System.out.println("Customer Information:");
            System.out.println("First Name: " + customer.getFirstName());
            System.out.println("Last Name: " + customer.getLastName());
            System.out.println("Username: " + customer.getUsername());
            System.out.println("ID: " + customer.getId());
        } else {
            System.out.println("Customer not found.");
        }
    }


    public void getAccountsInfo(String userID){
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_CSV_FILENAME))) {
            String line;
            while ((line=reader.readLine())!=null){
                String[] data = line.split(",");
                if (data.length>2 && data[2].equalsIgnoreCase(userID)){
                    System.out.println( "Account Number: " + data[0]);
                    System.out.println( "balance: " + data[1]);
                    System.out.println("Interest Rate: "+data[3]);
                    if (Integer.parseInt(data[4])==Enum.savingType()) {
                        System.out.println("Account Type: Saving");
                    }
                    if (Integer.parseInt(data[4])==Enum.checkingType()){
                        System.out.println("Account Type: Saving");
                    }

                    if (Integer.parseInt(data[5])==Enum.activeStatus()){
                        System.out.println("Status: Active");
                    }
                    if (Integer.parseInt(data[5])==Enum.inactiveStatus()){
                        System.out.println("Status: Inactive");
                    }
                    System.out.println("-----------------------------------------");


                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }




    public static List<String> getAccountInfoFromNumber(long accNumber) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_CSV_FILENAME))) {
            String line;
            List<String> info = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 2 && Long.parseLong(data[0]) == accNumber) {
                    info.add(data[0]);
                    info.add(data[1]);
                }
            }
            return info;
        }


    }
}













