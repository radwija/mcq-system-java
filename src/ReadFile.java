import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class ReadFile {
    public static void main(String[] args) {
        String data = null;
        try {
            File myObj = new File("src/question.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
//                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (data.trim() == "question") {
            System.out.println("Benar");
        } else {
            System.out.println("Salah");
        }

    }
}