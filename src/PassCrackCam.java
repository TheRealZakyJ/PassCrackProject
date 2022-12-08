import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;


public class PassCrackCam {

    private static String passType;
    private static String password;
    //private static StringBuilder guessPass = new StringBuilder("");

    private static ArrayList<String> gPass=new ArrayList<>();
    private static char[] charArr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

    private static int charLen = charArr.length;
    private static boolean solved = false;


    public static void main(String[] args) throws NoSuchAlgorithmException{
        // System.out.println("Your first argument is: "+args[0]);
        //password=args[0];
        password = "camoin";
        System.out.println(toHexString(THESHA256("GeeksForGeeks")));
        System.out.println(getMd5("GeeksForGeeks"));


    }



    public static String getMd5(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] digestion = md.digest(input.getBytes());
        byte[] messageDigest = md.digest(input.getBytes());

        BigInteger IIIINT = new BigInteger(1, messageDigest);

        String hashtext = IIIINT.toString(16);


        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    public static byte[] THESHA256(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        BigInteger num = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(num.toString(16));

        /*while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        } */

        return hexString.toString();
    }

    public static String toFLEXSTRING(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);

        StringBuilder FLEXSTRING = new StringBuilder(number.toString(16));

        while (FLEXSTRING.length() < 64)
        {
            FLEXSTRING.insert(0, '0');
        }

        return FLEXSTRING.toString();
    }
}



