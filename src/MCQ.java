import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MCQ {
    private final String mcqName;
    private final String filePath;
    private String userName;
    protected int questionsCounter;
    private int userCorrectAnswerSize;
    private int score;
    private List<String> userAnswer;
    private final Scanner input;
    private int answerNeededSize;
    private int emptyOptionsSize;
    private String range;
    protected String[] questionLineArray;
    private String[] correctAns;
    private String correctAnsText;
    private String character;

    // Constructor
    public MCQ(String mcqName, String filePath) {
        this.mcqName = mcqName;
        this.filePath = filePath;
        userCorrectAnswerSize = 0;
        questionsCounter = 0;
        input = new Scanner(System.in);
    }

    protected String getFilePath() {
        return filePath;
    }

    public void setUserName(String inputName) {
        this.userName = inputName;
    }

    protected String getUserName() {
        return this.userName;
    }

    protected String getMcqName() {
        return this.mcqName;
    }

    protected int getQuestionsCounter() {
        return questionsCounter;
    }

    private void setScore() {
        this.score = userCorrectAnswerSize * 100 / this.getQuestionsCounter();
    }

    private String showScore() {
        return this.score + "%";
    }

    private int getUserCorrectAnswerSize() {
        return this.userCorrectAnswerSize;
    }

    private int getUserWrongAnswer() {
        return this.getQuestionsCounter() - this.getUserCorrectAnswerSize();
    }

    private String getResult() {
        return this.getUserName() + ", you answered " + this.getUserCorrectAnswerSize() + " questions right, " + this.getUserWrongAnswer() + " questions wrong for total of " + this.getQuestionsCounter() + " questions.";
    }

    protected Integer getMCQType() {
        return Integer.parseInt(questionLineArray[7]);
    }

    protected int getAnswerNeededSize() {
        answerNeededSize = Integer.parseInt(questionLineArray[6]);
        return answerNeededSize;
    }

    protected void printNotFoundFile() {
        System.out.println("Oh no, the MCQ file not found:(");
    }

    public void doMCQ() {
        String currentLine;
        System.out.println("\n*** " + this.getMcqName() + " ***");

        try {
            FileReader fr = new FileReader(this.getFilePath());
            BufferedReader br = new BufferedReader(fr);

            System.out.println("Good luck, " + this.getUserName() + "!");
            while ((currentLine = br.readLine()) != null) {
                questionLineArray = currentLine.split(",");
                questionsCounter++;

                this.printQuestion();
                this.setCorrectAnswerAndValidation();
                this.setUserAnswer();
                this.checkUserAnswer();
            }
        } catch (Exception e) {
            this.printNotFoundFile();
            e.printStackTrace();
        }
        this.getStatus();
    }

    protected void getStatus() {
        this.setScore();
        System.out.println();
        System.out.println(this.getResult());
        System.out.println("Your score: " + this.showScore());
    }

    protected void printQuestion() {
        System.out.println("\n~ Question " + (this.getQuestionsCounter()) + " ~\n" + questionLineArray[0]);
        if (getAnswerNeededSize() > 1) {
            System.out.println(getAnswerNeededSize() + " answers needed *");
        }
    }

    protected void setCharacterAndRange() {
        for (int col = 1; col <= 4; col++) { // Determine character order of options
            if (col == 1) {
                character = "a";
            } else if (col == 2) {
                character = "b";
            } else if (col == 3) {
                character = "c";
            } else if (col == 4) {
                character = "d";
            }
            if (questionLineArray[col].equals("")) { // Determine range of character that user can input to answer
                emptyOptionsSize++; // Determine how many there are empty question
                if (emptyOptionsSize == 1) {
                    range = "c";
                } else if (emptyOptionsSize == 2) {
                    range = "b";
                } else if (emptyOptionsSize == 3) {
                    range = "a";
                }
            } else {
                System.out.println("   " + character + ". " + questionLineArray[col]);
            }
        }
    }

    protected void setCorrectAnswerAndValidation() {
        correctAns = questionLineArray[5].split("&"); // Separate the correct answer as array
        correctAnsText = "";
        // Getting the correct answer text of the single answer to be shown in answer correction
        if (Arrays.asList(correctAns).contains("a")) {
            correctAnsText = questionLineArray[1];
        } else if (Arrays.asList(correctAns).contains("b")) {
            correctAnsText = questionLineArray[2];
        } else if (Arrays.asList(correctAns).contains("c")) {
            correctAnsText = questionLineArray[3];
        } else if (Arrays.asList(correctAns).contains("d")) {
            correctAnsText = questionLineArray[4];
        }
    }

    private void resetEmptyOptionsSize() {
        /*
        This is important because if it is not reset,
        the emptyQuestionSize still has same value as the line of MCQ line before.
        */
        emptyOptionsSize = 0;
    }

    protected void setUserAnswer() {
        this.setCharacterAndRange();
        userAnswer = new ArrayList<>(); // Make user able to input multiple answer. Once user input the input added into the array list.
        for (int i = 0; i < getAnswerNeededSize(); i++) {
            String userInput;
            if (emptyOptionsSize > 0) {
                do {
                    System.out.print(">> Input the available options: ");
                    userInput = input.next().toLowerCase();
                    if (userAnswer.contains(userInput)) {
                        System.out.println("   You can't input the same input as before!");
                    } else if (!userInput.matches("[a-" + range + "A-" + range.toUpperCase() + "]")) {
                        System.out.println("   Your input is invalid!");
                    }
                } while (!userInput.matches("[a-" + range + "A-" + range.toUpperCase() + "]") || userAnswer.contains(userInput));
                userAnswer.add(userInput);
            } else {
                do {
                    System.out.print(">> Input the available options: ");
                    userInput = input.next().toLowerCase();
                    if (userAnswer.contains(userInput)) {
                        System.out.println("   You can't input the same input as before!");
                    } else if (!userInput.matches("[a-dA-D]")) {
                        System.out.println("   Your input is invalid!");
                    }
                } while (!userInput.matches("[a-dA-D]") || userAnswer.contains(userInput));
                userAnswer.add(userInput);
            }
        }
        this.resetEmptyOptionsSize();
    }

    protected void checkUserAnswer() {
        if (userAnswer.containsAll(Arrays.asList(correctAns))) {
            System.out.println("   ✅ Great, your answer is correct!");
            userCorrectAnswerSize++;
        } else if (correctAns.length > 1) {
            System.out.println("   ❌ Your answers are wrong. The right answer are");
            for (String correctAn : correctAns) {
                switch (correctAn) {
                    case "a":
                        correctAnsText = questionLineArray[1];
                        break;
                    case "b":
                        correctAnsText = questionLineArray[2];
                        break;
                    case "c":
                        correctAnsText = questionLineArray[3];
                        break;
                    case "d":
                        correctAnsText = questionLineArray[4];
                        break;
                }
                System.out.println("      " + correctAn + ". " + correctAnsText);
            }

        } else {
            System.out.println("   ❌ Your answer is wrong. The right answer is " + correctAns[0] + ". " + correctAnsText);
        }
    }
}