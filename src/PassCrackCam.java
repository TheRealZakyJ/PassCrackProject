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
        //password=args[0];
        password = "monk";
        System.out.println(getMd5(password));
        /*passType = "brute";

        if(passType.equals("brute")) {

            while (!solved) {
                // guessPass.append(charArr[0]);
                gPass.add(Character.toString(charArr[0]));
                //System.out.println(guessPass.length()+"len");
                for (int i = 0; i < gPass.size()-1; i++) {
                    for (int j = 0; j < charLen; j++) {
                        //guessPass.setCharAt(i, charArr[j]);
                        gPass.set(i,Character.toString(charArr[j]));
                        //System.out.println(gPass.toString() + "forst" + i + j);
                        solved = brute(i + 1);
                        //System.out.println("sec");
                    }
                }
            }
        }
        //PassCrackCam encrypter = new PassCrackCam();

         */



    }

    public static boolean brute(int index) {
        for (int i = 0; i < charLen; i++) {
            // guessPass.setCharAt(index, charArr[i]);
            gPass.set(index,Character.toString(charArr[i]));
            System.out.println(String.join("",gPass));
           /* if (guessPass.toString().equals(password)) {
                System.out.println("Here is the password: " + guessPass);
                solved = true;
                return true;
                //break;
                //System.exit(0);
            }

            */
            if (String.join("",gPass).equals(password)) {
                System.out.println("Here is the password: " + String.join("",gPass));
                solved = true;
                //return true;
                //break;.ja
                System.exit(0);
            }
            /*if (index < guessPass.length()-1) {
                brute(index + 1);
            }

             */
            if (index < gPass.size()-1) {
                brute(index + 1);
            }
        }
        //System.out.println(guessPass.toString() + "end");
        return false;
    }

    public static String getMd5(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] digestion = md.digest(input.getBytes());

        BigInteger IIIINT = new BigInteger(digestion);

        return IIIINT.toString();
    }


}


