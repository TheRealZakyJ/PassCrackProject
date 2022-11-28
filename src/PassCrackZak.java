import java.util.*;
public class PassCrackZak {

    private static String passType = "brute";
    private static String password = "abc";
    private static StringBuilder guessPass = new StringBuilder("");

    private static ArrayList<String> gPass=new ArrayList<>();
    private static char[] charArr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

    private static int charLen = charArr.length;
    private static boolean solved = false;

    private static int lastInd = guessPass.length()-1;



    public static void main(String[] args) {
        //password=args[0];
        //passType = args[1];
        if(passType.equals("brute")) {

            while (!solved) {
                guessPass.append(charArr[0]);
                gPass.add(Character.toString(charArr[0]));
                System.out.println(guessPass.length()+"len");
                for (int i = 0; i < guessPass.length()-1; i++) {
                    for (int j = 0; j < charLen; j++) {
                        guessPass.setCharAt(i, charArr[j]);
                        gPass.set(i,Character.toString(charArr[j]));
                        System.out.println(guessPass.toString() + "forst" + i + j);
                        solved = brute(i + 1);
                        //System.out.println("sec");
                    }
                }
            }
        }
    }

    public static boolean brute(int index) {
        for (int i = 0; i < charLen; i++) {
            guessPass.setCharAt(index, charArr[i]);
            gPass.set(index,Character.toString(charArr[i]));
            System.out.println(gPass.toString() + " in");
            if (guessPass.toString().equals(password)) {
                System.out.println("Here is the password: " + guessPass);
                solved = true;
                return true;
                //break;
                //System.exit(0);
            }
            if (String.join(", ",gPass).equals(password)) {
                System.out.println("Here is the password: " + String.join(", ",gPass));
                solved = true;
                return true;
                //break;
                //System.exit(0);
            }
            if (index < guessPass.length()-1) {
                brute(index + 1);
            }
        }
        System.out.println(guessPass.toString() + "end");
        return false;
    }

}


