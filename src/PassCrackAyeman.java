import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.invoke.StringConcatFactory;
import java.util.*;


public class PassCrackAyeman {
    private static ArrayList<String> wordList;
    private static String password = "";
    private static String passType = "";



       public static void main(String[] args) throws FileNotFoundException {

           Scanner s = new Scanner(new File("10k-most-common.txt"));
           ArrayList<String> list = new ArrayList<>();
           String[] list1 = new String[10000];
           while (s.hasNext()){
               String line = s.nextLine();
               list.add(line);


           }

           wordList=list;

           password = args[0];
           passType = args[1];

           if(passType.equals("dict")) {
               dict(password);
           }

       }

    public static ArrayList<String> get_arraylist_from_file(File f) throws FileNotFoundException {
        Scanner s= new Scanner(f);
        ArrayList<String> list = new ArrayList<>();

        while (s.hasNext()) {
            list.add(s.next());
       }
        s.close();

       return list;

    }

    public static void dict(String pass){
        if(wordList.contains(pass)){
            System.out.println(wordList.get(wordList.indexOf(pass)));
        }else{
            System.out.println("Not in password list.");
        }
    }


}
