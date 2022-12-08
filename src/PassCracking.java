import java.io.File;
import java.io.FileNotFoundException;
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


    public static void main(String[] args) throws FileNotFoundException {

        password = args[0];
        passType = args[1];
        hashOrNah = args[2];
        if (passType.equals("brute") && (hashOrNah.equals("-p"))) {

            while (!solved) {

                gPass.add(Character.toString(charArr[0]));

                for (int i = 0; i < gPass.size() - 1; i++) {
                    for (int j = 0; j < charLen; j++) {

                        gPass.set(i, Character.toString(charArr[j]));
                        solved = brute(i + 1);

                    }
                }
            }
        } else if (passType.equals("dict") && (hashOrNah.equals("-p"))) {
            Scanner s = new Scanner(new File("10k-most-common.txt"));
            ArrayList<String> list = new ArrayList<>();
            //String[] list1 = new String[10000];
            while (s.hasNext()) {
                String line = s.nextLine();
                list.add(line);
            }
            wordList = list;
            dict(password);

        }
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


    public static void dict(String pass){
        if(wordList.contains(pass)){
            System.out.println(wordList.get(wordList.indexOf(pass))+wordList.indexOf(pass));
        }else{
            System.out.println("Not in password list.");
        }
    }

}

























