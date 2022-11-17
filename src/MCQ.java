import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MCQ {
    public String mcqSetName;
    public String filePath;
    public int amountOfField;
    private String[][] questions;

    private int correctAnswer;

    public MCQ(String mcqSetName, String filePath, int amountOfField) {
        this.mcqSetName = mcqSetName;
        this.filePath = filePath;
        this.amountOfField = amountOfField;
        correctAnswer = 0;

        // Construct MCQ set
        List<String> recordList = new ArrayList<String>();
        String delimiter = ",";
        String currentLine;

        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                recordList.add(currentLine);
            }
            int recordCount = recordList.size();

            String arrayToReturn[][] = new String[recordCount][this.amountOfField];
            String[] data;

            for (int i = 0; i < recordCount; i++) {
                data = recordList.get(i).split(delimiter);
                for (int j = 0; j < data.length; j++) {
                    arrayToReturn[i][j] = data[j];
                }
            }
            this.questions = arrayToReturn;
        } catch (Exception e) {
            System.out.println(e);
            this.questions = null;
        }
    }

    public void doMCQ() {
        for (int row = 0; row < this.questions.length; row++) {
            Scanner input = new Scanner(System.in);
            System.out.println((row + 1) + ". " + this.questions[row][0]);
            System.out.println("   a. " + this.questions[row][1]);
            System.out.println("   b. " + this.questions[row][2]);
            System.out.println("   c. " + this.questions[row][3]);
            System.out.println("   d. " + this.questions[row][4]);
            String correctAns = this.questions[row][5];
            System.out.print("Input your answer: ");
            String userAnswer = input.next();
            if (correctAns.equalsIgnoreCase(userAnswer)) {
                System.out.println("Benar");
                correctAnswer++;
            } else {
                System.out.println("Salah");
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "src/question.csv";
        MCQ q = new MCQ("Java Basic", filePath, 6);
//        String[][] data = q.getQuestionData();
        System.out.println(q.questions.length);
        int correctAnswer = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Your score: " + correctAnswer);

    }
}
