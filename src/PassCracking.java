import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


public class PassCracking {

    private static String passType;
    private static String password;
    private static ArrayList<String> gPass=new ArrayList<>();
    private static char[] charArr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    private static int charLen = charArr.length;
    private static boolean solved = false;
    private static String hashOrNah;
    private static ArrayList<String> wordList;
    private static String hashType;


    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        password = args[0];
       passType = args[1];
       hashOrNah = args[2];
        if (passType.equals("brute")) {
            hashType=identify(password);
            while (!solved) {

                gPass.add(Character.toString(charArr[0]));

                for (int i = 0; i < gPass.size() - 1; i++) {
                    for (int j = 0; j < charLen; j++) {

                        gPass.set(i, Character.toString(charArr[j]));
                        solved = brute(i + 1);

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
    public static String hashbrute(int index, String hashType){
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
        return "";
    }


    public static void dict(String pass){
        if(wordList.contains(pass)){
            System.out.println(wordList.get(wordList.indexOf(pass)));
        }else{
            System.out.println("Not in password list.");
        }
    }

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
                hashedDictWord=getMd5(wordList.get(i));
                if(hashedDictWord.equals(wordList.get(i))){
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
    public static String getMd5(String input) throws NoSuchAlgorithmException {
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
}

























