import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MCQ{
    public String questionSetName;
    public String filePath;
    public int point;

    public MCQ(String questionSetName, String filePath) {
        this.questionSetName = questionSetName;
        this.filePath = filePath;
        point = 0;
    }

//    public void answerTheMCQ() {
//        File f = new File(filePath)
//        Scanner q = new Scanner(f);
//        try {
//            while (q.hasNextLine()) {
//                String line = q.nextLine();
//                String[] lineArray= new String[6];
//
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println(e);
//        }
//    }
}
