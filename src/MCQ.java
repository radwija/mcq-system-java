import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MCQ {
    private final String mcqName;
    private final String filePath;
    private String userName;
    private int questionsCounter;
    private int userCorrectAnswer;
    private int score;

    // Constructor
    public MCQ(String mcqName, String filePath) {
        this.mcqName = mcqName;
        this.filePath = filePath;
        userCorrectAnswer = 0;
        questionsCounter = 0;
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

    public int getQuestionsCounter() {
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

    private String getStatus() {
        return "\n" + this.getUserName() + ", you answered " + this.getUserCorrectAnswer() + " questions right, " + this.getWrongAnswer() + " questions wrong for total of " + this.getQuestionsCounter() + " questions.";
    }

    public void doMCQ() {
        Scanner input = new Scanner(System.in);

        String delimiter = ",";
        String currentLine;
        String[] questionLine;

        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                questionLine = currentLine.split(delimiter);
                questionsCounter++;

                System.out.println("\n~ Question " + (this.getQuestionsCounter()) + " ~\n" + questionLine[0]);
                int noOfAnswerNeeded = Integer.parseInt(questionLine[6]);
                if (noOfAnswerNeeded > 1) {
                    System.out.println(noOfAnswerNeeded + " answers needed *");
                }
                String character = null;
                String range = null;
                int emptyOptions = 0;
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
                        emptyOptions++; // Determine how many there are empty question
                        if (emptyOptions == 1) {
                            range = "c";
                        } else if (emptyOptions == 2) {
                            range = "b";
                        } else if (emptyOptions == 3) {
                            range = "a";
                        }
                    } else {
                        System.out.println("   " + character + ". " + questionLine[col]);
                    }
                }
                String[] correctAns = questionLine[5].split("&"); // Separate the correct answer as array
                String correctAnsText = "";
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

                String userInput;
                List<String> userAnswer = new ArrayList<>(); // Make user able to input multiple answer. Once user input the input added into the array list.
                for (int i = 0; i < noOfAnswerNeeded; i++) {
                    if (noOfAnswerNeeded == 1) {
                        if (emptyOptions > 0) {
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
                    } else if (noOfAnswerNeeded > 1) {
                        if (emptyOptions > 0) {
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
                }

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setScore();
        System.out.println("Your correct answer: " + this.getUserCorrectAnswer());
        System.out.println(this.getStatus());
        System.out.println("Your score: " + this.showScore());
    }
}