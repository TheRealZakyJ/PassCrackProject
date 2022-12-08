import java.util.*;
public class PassCrackZak {

    private static String passType;
    private static String password;
    private static ArrayList<String> gPass=new ArrayList<>();
    private static char[] charArr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    private static int charLen = charArr.length;
    private static boolean solved = false;


    public static void main(String[] args) {

        password=args[0];
        passType = args[1];
        if(passType.equals("brute")) {

            while (!solved) {

                gPass.add(Character.toString(charArr[0]));

                for (int i = 0; i < gPass.size()-1; i++) {
                    for (int j = 0; j < charLen; j++) {

                        gPass.set(i,Character.toString(charArr[j]));
                        solved = brute(i + 1);

                    }
                }
            }
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
}


