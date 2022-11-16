import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question {
    public String mcqSetName;
    public String filePath;
    public int amountOfField;

    public String[][] questions;

    public Question(String mcqSetName, String filePath, int amountOfField) {
        this.mcqSetName = mcqSetName;
        this.filePath = filePath;
        this.amountOfField = amountOfField;

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

    public String getQuestionData(int row, int column) {
        return questions[row][column];
    }

    public static void main(String[] args) {
        String filePath = "src/question.csv";
        Question q = new Question("Java Basic", filePath, 6);
//        String[][] data = q.getQuestionData();
        System.out.println(q.questions.length);
        int correctAnswer = 0;
        Scanner input = new Scanner(System.in);


        for (int row = 0; row < q.questions.length; row++) {
            System.out.println((row + 1) + ". " + q.questions[row][0]);
            System.out.println("   a. " + q.questions[row][1]);
            System.out.println("   b. " + q.questions[row][2]);
            System.out.println("   c. " + q.questions[row][3]);
            System.out.println("   d. " + q.questions[row][4]);
            String correctAns = q.questions[row][5];
            System.out.print("Input your answer: ");
            String userAnswer = input.next();
            if (correctAns.equalsIgnoreCase(userAnswer)) {
                System.out.println("Benar");
                correctAnswer++;
            } else {
                System.out.println("Salah");
            }
        }

        System.out.println("Your score: " + correctAnswer);

    }
}
