import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;


public class PassCrackCam {

    private static String passType;
    private static String password;
    //private static StringBuilder guessPass = new StringBuilder("");

    private static ArrayList<String> gPass=new ArrayList<>();
    private static char[] charArr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

    private static int charLen = charArr.length;
    private static boolean solved = false;

    //private static int lastInd = guessPass.length()-1;



    public static void main(String[] args) throws NoSuchAlgorithmException{
        // System.out.println("Your first argument is: "+args[0]);
        password=args[0];
        //password = "camoin";
        System.out.println(getMd5(password));


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

    public static String bcrypt(String input) {

        return null;
    }
}



