import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MCQ_1 {
    private final String mcqSetName;
    private final String filePath;
    private String name;
    private int questionsCounter;
    private int userCorrectAnswer;
    public int score;

    public MCQ_1(String mcqSetName, String filePath) {
        this.mcqSetName = mcqSetName;
        this.filePath = filePath;
        userCorrectAnswer = 0;
        questionsCounter = 0;
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

    public void setScore() {
        this.score = userCorrectAnswer * 100 / this.questionsCounter;
    }

    public String showScore() {
        return this.score + "%";
    }

    public int getUserCorrectAnswer() {
        return this.userCorrectAnswer;
    }

    public int getWrongAnswer() {
        return this.getUserCorrectAnswer() - this.questionsCounter;
    }

    public String getStatus() {
        return ", you answered " + this.getUserCorrectAnswer() + " questions right, " + this.getWrongAnswer() + " questions wrong for total of " + this.questionsCounter + " questions.";
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

                System.out.println("\n~ Question " + (questionsCounter) + " ~\n" + questionLine[0]);
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
                            } while (!userInput.matches("[a-" + range + "A-" + range.toUpperCase() + "]"));
                            userAnswer.add(userInput);
                        } else {
                            do {
                                System.out.print(">> Input the available options: ");
                                userInput = input.next().toLowerCase();
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
                                }
                            } while (!userInput.matches("[a-" + range + "A-" + range.toUpperCase() + "]") || userAnswer.contains(userInput));
                            userAnswer.add(userInput);
                        } else {
                            do {
                                System.out.print(">> Input the available options: ");
                                userInput = input.next().toLowerCase();
                                if (userAnswer.contains(userInput)) {
                                    System.out.println("   You can't input the same input as before!");
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
                    for (int j = 0; j < correctAns.length; j++) {
                        if (correctAns[j].equals("a")) {
                            correctAnsText = questionLine[1];
                        } else if (correctAns[j].equals("b")) {
                            correctAnsText = questionLine[2];
                        } else if (correctAns[j].equals("c")) {
                            correctAnsText = questionLine[3];
                        } else if (correctAns[j].equals("d")) {
                            correctAnsText = questionLine[4];
                        }
                        System.out.println("      " + correctAns[j] + ". " + correctAnsText);
                    }

                } else {
                    System.out.println("   ❌ Your answer is wrong. The right answer is " + correctAns[0] + ". " + correctAnsText);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            questionLine = null;
        }
        this.setScore();
        System.out.println("Your correct answer: " + this.getUserCorrectAnswer());
        System.out.println(this.getName() + this.getStatus());
        System.out.println("Your score: " + this.showScore());
    }
}