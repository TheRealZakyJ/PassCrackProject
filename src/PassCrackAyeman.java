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
               System.out.println(line);
               list.add(line);
               //list[]

           }
           /*for(int i=0;i< list1.length;i++){
               String line = s.nextLine();
               System.out.println(line);
               list1[i]=line;
           }

            */
           //s.close();
           wordList=list;
           //Arrays.toString(list1);
           /*
           File f = new File("10k-most-common.txt");
           try{
               wordList = get_arraylist_from_file(f);
               //for(int x = 0; x < wordList.size(); x++){
               // System.out.println(wordList.get(x));
               // }


           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }

            */

           //Scanner s = new Scanner(new File("10k-most-common.txt"));
           //System.out.println(s.next());

           //for(String line: FileUtils.readLines("10k-most-common.txt"))
           //    System.out.print(line);
           password = args[0];
           passType = args[1];

           //System.out.println("dn");
           if(passType.equals("dict")) {
               dict(password);
           }
           //System.out.println("don1");
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
            System.out.println(wordList.indexOf(pass));
        }else{
            System.out.println("Not in password list.");
        }
    }


}
