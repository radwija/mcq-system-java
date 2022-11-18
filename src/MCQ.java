import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MCQ {
    private String mcqSetName;
    private String filePath;
    private int amountOfField;
    public String[][] questions;
    private int correctAnswer;
    public double score;
    public String[] data;
    public List<String> recordList = new ArrayList<String>();

    public MCQ(String mcqSetName, String filePath, int amountOfField) {
        this.mcqSetName = mcqSetName;
        this.filePath = filePath;
        this.amountOfField = amountOfField;
        correctAnswer = 0;

        // Construct MCQ set
//        List<String> recordList = new ArrayList<String>();
        String delimiter = ",";
        String currentLine;

        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                data = currentLine.split(delimiter);
                recordList.add(currentLine);
            }
            int recordCount = recordList.size();

//            String arrayToReturn[][] = new String[recordCount][this.amountOfField];
//            String[] data;
//
//            for (int i = 0; i < recordCount; i++) {
//                data = recordList.get(i).split(delimiter);
//                for (int j = 0; j < data.length; j++) {
//                    arrayToReturn[i][j] = data[j];
//                }
//            }
//            this.questions = arrayToReturn;
        } catch (Exception e) {
            System.out.println(e);
            this.questions = null;
        }
    }

    public String getMcqSetName() {
        return this.mcqSetName;
    }

    public String showScore() {
        return (correctAnswer * 100 / this.questions.length) + "%";
    }

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }

    public int getWrongAnswer() {
        return this.getCorrectAnswer() - this.questions.length;
    }

    public void printLines(int no) {
        System.out.println(recordList.get(9));
    }

    public void doMCQ() {
        for (int row = 0; row < this.questions.length; row++) {
            Scanner input = new Scanner(System.in);
            System.out.println((row + 1) + ". " + this.questions[row][0]);
//            for (int j = 0; j< )
            System.out.println("   a. " + this.questions[row][1]);
            System.out.println("   b. " + this.questions[row][2]);
            System.out.println("   c. " + this.questions[row][3]);
            System.out.println("   d. " + this.questions[row][4]);
            String correctAns = this.questions[row][5];
            String userAnswer;
            do {
                System.out.print("You can only input a, b, c, or d here: ");
                userAnswer = input.next();
            } while (!userAnswer.matches("[a-dA-D]"));
            if (correctAns.equalsIgnoreCase(userAnswer)) {
                System.out.println("Great, your answer is correct!\n");
                correctAnswer++;
            } else {
                System.out.println("Your answer is wrong. The right answer is " + correctAns + "\n");
            }
        }
        System.out.println("Your correct answer: " + this.getCorrectAnswer());
        System.out.println("<Name here>, you answered" + this.getCorrectAnswer() + "questions right, " + (this.questions.length - this.getCorrectAnswer()) + "questions wrong for total of " + this.questions.length + " questions.");
        System.out.println("Your score: " + this.showScore());
    }

    public static void main(String[] args) {
        MCQ java = new MCQ("Java Basic", "src/java-basic.csv",6);
        java.printLines(1);
    }

}
