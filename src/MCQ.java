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
    private int userCorrectAnswer;
    private int score;
    private List<String> userAnswer;
    private Scanner input;
    private int answerNeededSize;
    private int emptyOptionsSize;
    private String range;
    protected String[] questionLine;
    private String[] correctAns;
    private String correctAnsText;
    private String character;

    // Constructor
    public MCQ(String mcqName, String filePath) {
        this.mcqName = mcqName;
        this.filePath = filePath;
        userCorrectAnswer = 0;
        questionsCounter = 0;
        input = new Scanner(System.in);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setUserName(String inputName) {
        this.userName = inputName;
    }

    private String getUserName() {
        return this.userName;
    }

    public String getMcqName() {
        return this.mcqName;
    }

    protected int getQuestionsCounter() {
        return questionsCounter;
    }

    private void setScore() {
        this.score = userCorrectAnswer * 100 / this.getQuestionsCounter();
    }

    private String showScore() {
        return this.score + "%";
    }

    private int getUserCorrectAnswer() {
        return this.userCorrectAnswer;
    }

    private int getWrongAnswer() {
        return this.getQuestionsCounter() - this.getUserCorrectAnswer();
    }

    private String getResult() {
        return "\n" + this.getUserName() + ", you answered " + this.getUserCorrectAnswer() + " questions right, " + this.getWrongAnswer() + " questions wrong for total of " + this.getQuestionsCounter() + " questions.";
    }

    public Integer getMCQType() {
        return Integer.parseInt(questionLine[7]);
    }

    protected int getAnswerNeededSize() {
        answerNeededSize = Integer.parseInt(questionLine[6]);
        return answerNeededSize;
    }

    public void doMCQ() {
        String delimiter = ",";
        String currentLine;

        try {
            FileReader fr = new FileReader(this.getFilePath());
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                questionLine = currentLine.split(delimiter);
                questionsCounter++;

                this.printQuestion();
                this.setSingleAnswerValidation();
                this.setUserAnswer();
                this.checkUserAnswer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getStatus();
    }

    protected void getStatus() {
        this.setScore();
        System.out.println("Your correct answer: " + this.getUserCorrectAnswer());
        System.out.println(this.getResult());
        System.out.println("Your score: " + this.showScore());
    }

    protected void printQuestion() {
        System.out.println("\n~ Question " + (this.getQuestionsCounter()) + " ~\n" + questionLine[0].toString());
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
            if (questionLine[col].equals("")) { // Determine range of character that user can input to answer
                emptyOptionsSize++; // Determine how many there are empty question
                if (emptyOptionsSize == 1) {
                    range = "c";
                } else if (emptyOptionsSize == 2) {
                    range = "b";
                } else if (emptyOptionsSize == 3) {
                    range = "a";
                }
            } else {
                System.out.println("   " + character + ". " + questionLine[col]);
            }
        }
    }

    protected String setSingleAnswerValidation() {
        correctAns = questionLine[5].split("&"); // Separate the correct answer as array
        correctAnsText = "";
        // Getting the correct answer text to be shown in answer correction
        if (Arrays.asList(correctAns).contains("a")) {
            correctAnsText = questionLine[1];
        } else if (Arrays.asList(correctAns).contains("b")) {
            correctAnsText = questionLine[2];
        } else if (Arrays.asList(correctAns).contains("c")) {
            correctAnsText = questionLine[3];
        } else if (Arrays.asList(correctAns).contains("d")) {
            correctAnsText = questionLine[4];
        }
        return correctAnsText;
    }

    protected void resetEmptyOptionsSize() {
        emptyOptionsSize = 0;
    }

    protected List<String> setUserAnswer() {
        this.setCharacterAndRange();
        userAnswer = new ArrayList<>(); // Make user able to input multiple answer. Once user input the input added into the array list.
        for (int i = 0; i < getAnswerNeededSize(); i++) {
            String userInput;
            if (getAnswerNeededSize() == 1) {
                if (emptyOptionsSize > 0) {
                    do {
                        System.out.print(">> Input the available options: ");
                        userInput = input.next().toLowerCase();
                        if (!userInput.matches("[a-" + range + "A-" + range.toUpperCase() + "]")) {
                            System.out.println("   Your input is invalid!");
                        }
                    } while (!userInput.matches("[a-" + range + "A-" + range.toUpperCase() + "]"));
                    userAnswer.add(userInput);
                } else {
                    do {
                        System.out.print(">> Input the available options: ");
                        userInput = input.next().toLowerCase();
                        if (!userInput.matches("[a-dA-D]")) {
                            System.out.println("   Your input is invalid!");
                        }
                    } while (!userInput.matches("[a-dA-D]"));
                    userAnswer.add(userInput);
                }
            } else {
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
        return userAnswer;
    }

    public void checkUserAnswer() {
        if (userAnswer.containsAll(Arrays.asList(correctAns))) {
            System.out.println("   ✅ Great, your answer is correct!");
            userCorrectAnswer++;
        } else if (correctAns.length > 1) {
            System.out.println("   ❌ Your answers are wrong. The right answer are");
            for (String correctAn : correctAns) {
                switch (correctAn) {
                    case "a":
                        correctAnsText = questionLine[1];
                        break;
                    case "b":
                        correctAnsText = questionLine[2];
                        break;
                    case "c":
                        correctAnsText = questionLine[3];
                        break;
                    case "d":
                        correctAnsText = questionLine[4];
                        break;
                }
                System.out.println("      " + correctAn + ". " + correctAnsText);
            }

        } else {
            System.out.println("   ❌ Your answer is wrong. The right answer is " + correctAns[0] + ". " + correctAnsText);
        }
    }
}