import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MCQ {
    private String mcqSetName;
    private String filePath;
    private String name;

    private int amountOfField;
    private String[][] questions;
    private int correctAnswer;
    public double score;

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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            this.questions = null;
        }
    }

    public void setName(String inputName) {
        this.name = inputName;
    }

    public String getName() {
        return this.name;
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

    public String getStatus() {
        return ", you answered " + this.getCorrectAnswer() + " questions right, " + (this.questions.length - this.getCorrectAnswer()) + " questions wrong for total of " + this.questions.length + " questions.";
    }

    public void doMCQ() {
        Scanner input = new Scanner(System.in);

        for (int row = 0; row < this.questions.length; row++) {
            System.out.println("~ Question "+(row + 1) + " ~\n" + this.questions[row][0]);
            String character = null;
            String range = null;
            int emptyOptions = 0;
            for (int col = 1; col <= 4; col++) {
                if (col == 1) {
                    character = "a";
                } else if (col == 2) {
                    character = "b";
                } else if (col == 3) {
                    character = "c";
                } else if (col == 4) {
                    character = "d";
                }
                if (this.questions[row][col].equals("")) {
                    emptyOptions++;
                    if (emptyOptions == 1) {
                        range = "c";
                    } else if (emptyOptions == 2) {
                        range = "b";
                    } else if (emptyOptions == 3) {
                        range = "a";
                    }
                } else {
                    System.out.println("   " + character + ". " + this.questions[row][col]);
                }
            }
            String correctAns = this.questions[row][5];
            String correctAnsText = null;
            if (correctAns.equals("a")) {
                correctAnsText = questions[row][1];
            } else if (correctAns.equals("b")) {
                correctAnsText = questions[row][2];
            } else if (correctAns.equals("c")) {
                correctAnsText = questions[row][3];
            } else if (correctAns.equals("d")) {
                correctAnsText = questions[row][4];
            }
            String userAnswer;

            if (emptyOptions > 0) {
                do {
                    System.out.print(">> Input the available options: ");
                    userAnswer = input.next();
                } while (!userAnswer.matches("[a-" + range + "A-" + range.toUpperCase() + "]"));
            } else {
                do {
                    System.out.print(">> Input the available options: ");
                    userAnswer = input.next();
                } while (!userAnswer.matches("[a-dA-D]"));
            }
            if (correctAns.equalsIgnoreCase(userAnswer)) {
                System.out.println("   ✅ Great, your answer is correct!\n");
                correctAnswer++;
            } else {
                System.out.println("   ❌ Your answer is wrong. The right answer is " + correctAns + ". " + correctAnsText + "\n");
            }
        }
        System.out.println("Your correct answer: " + this.getCorrectAnswer());
        System.out.println(this.getName() + this.getStatus());
        System.out.println("Your score: " + this.showScore());
    }

}