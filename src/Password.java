import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password
{
    public static String hashPassword(String plainPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(plainPassword.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyPassword(String inputPassword, String hashedPassword){
        if (hashedPassword.equals(hashPassword(inputPassword))) {
            return true;
        }
        else {
            return false;
        }
    }
}