import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


public class PassCracking {
    //initializing private variables
    private static String passType;
    private static String password;
    private static ArrayList<String> gPass=new ArrayList<>();
    private static char[] charArr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    private static int charLen = charArr.length;
    private static boolean solved = false;
    private static String hashOrNah;
    private static ArrayList<String> wordList;
    private static String hashType;

    //setting up main method for command line arguements.
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        //setting private vars equal to what is passed in the command line
        password = args[0];
       passType = args[1];
       hashOrNah = args[2];
       //checks for if the user prompts a brute or dictionary attack and if it is plaintext or hashed
        if (passType.equals("brute")) {
            if(hashOrNah.equals("-p")) {
                while (!solved) {

                    gPass.add(Character.toString(charArr[0]));

                    for (int i = 0; i < gPass.size() - 1; i++) {
                        for (int j = 0; j < charLen; j++) {

                            gPass.set(i, Character.toString(charArr[j]));
                            solved = brute(i + 1);

                        }
                    }
                }
            }else if(hashOrNah.equals("-h")){
                hashType=identify(password);
                while (!solved) {

                    gPass.add(Character.toString(charArr[0]));

                    for (int i = 0; i < gPass.size() - 1; i++) {
                        for (int j = 0; j < charLen; j++) {

                            gPass.set(i, Character.toString(charArr[j]));
                            solved = hashbrute(i + 1,hashType);

                        }
                    }
                }
            }
        } else if (passType.equals("dict")) {
            Scanner s = new Scanner(new File("10k-most-common.txt"));
            ArrayList<String> list = new ArrayList<>();
            //String[] list1 = new String[10000];
            while (s.hasNext()) {
                String line = s.nextLine();
                list.add(line);
            }
            wordList = list;
            if(hashOrNah.equals("-p")) {
                dict(password);
            }else if(hashOrNah.equals("-h")){
                hashType=identify(password);
                System.out.println(hashDict(hashType,password));
            }
        }
    }
    //identifies the hash type that inputted in the command line
    public static String identify(String password){
        String hash = "";
        if(password.indexOf("$2") == 0)
        {
            hash = "-b";
        }
        else if(password.length() == 32)
        {
            hash = "-m";
        }
        else if(password.length() == 64)
        {
            hash = "-s";
        }
        return hash;
    }

    //Recursive method that iterates through a list of chars until it has found the correct password
    public static boolean brute(int index) {
        for (int i = 0; i < charLen; i++) {

            gPass.set(index,Character.toString(charArr[i]));
            System.out.println(String.join("",gPass));

            if (String.join("",gPass).equals(password)) {
                System.out.println("Here is the password: " + String.join("",gPass));
                solved = true;
                System.exit(0);
            }

            if (index < gPass.size()-1) {
                brute(index + 1);
            }
        }
        return false;
    }
    //method that finds the plaintext password from a hash
    //hashes every iteration of a password from a char list and compares it to the hashed password.
    public static boolean hashbrute(int index, String hashType) throws NoSuchAlgorithmException {
        for (int i = 0; i < charLen; i++) {
            String passString= String.join("",gPass);
            gPass.set(index,Character.toString(charArr[i]));
            System.out.println(getMd5(passString));
            if(hashType.equals("-m")) {
                if (getMd5(passString).equals(password)) {
                    System.out.println("Here is the password: " + passString);
                    solved = true;
                    System.exit(0);
                }
            }

            if (index < gPass.size()-1) {
                hashbrute(index + 1,hashType);
            }
        }
        return false;
    }


    //method that goes through a dictionary of the 10000 most common passwords, and returns the correct one.
    public static void dict(String pass){
        if(wordList.contains(pass)){
            System.out.println(wordList.get(wordList.indexOf(pass)));
        }else{
            System.out.println("Not in password list.");
        }
    }

    //iterates through dictionary to compare hashes from there with the password inputted from the command line
    public static String hashDict(String hashType,String password) throws NoSuchAlgorithmException {
        String plaintext="No password found.";
        String hashedDictWord ="";

        for(int i =0;i<wordList.size();i++){
            if(hashType.equals("-m")){
                hashedDictWord=getMd5(wordList.get(i));
                if(hashedDictWord.equals(password)){
                    plaintext=wordList.get(i);
                    break;
                }
            }else if(hashType.equals("-s")){
                hashedDictWord=toFLEXSTRING(THESHA256(wordList.get(i)));
                if(hashedDictWord.equals(password)){
                    plaintext=wordList.get(i);
                    break;
                }
            }else if(hashType.equals("-b")){
                hashedDictWord=getMd5(wordList.get(i));
                if(hashedDictWord.equals(wordList.get(i))){
                    plaintext=wordList.get(i);
                    break;
                }
            }

        }
        return plaintext;
    }
    //generates an MD5 hash
    public static String getMd5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        //byte[] digestion = md.digest(input.getBytes());
        byte[] messageDigest = md.digest(input.getBytes());

        BigInteger IIIINT = new BigInteger(1, messageDigest);

        String hashtext = IIIINT.toString(16);


        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
    //generates a SHA256 hash
    public static byte[] THESHA256(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    //
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

























