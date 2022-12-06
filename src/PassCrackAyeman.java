import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import

public class PassCrackAyeman {
    public static void main(String[] args) {
        //Scanner s = new Scanner(new File("words.txt"));
        //System.out.println(s.next());
        for(String line: FileUtils.readLines("10k-most-common.txt"))
            System.out.println(line);
    }
}
