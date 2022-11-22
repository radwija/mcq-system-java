import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MCQExecutor {
    private List<MCQ> objectsToRun;
    private final Scanner input;
    private String userName;
    private int chosenMcq;

    public MCQExecutor(List<MCQ> objectsToRun) {
        this.objectsToRun = objectsToRun;
        input = new Scanner(System.in);
    }

    public void executeMcq() {
        System.out.print("Hi, how can we address you?\n>> Enter your name: ");
        String userNameInput = input.nextLine();
        setUserName(userNameInput);
        System.out.println("\nWelcome to our MCQ on IT, " + this.getUserName() + ":)");
        showMCQLists();
        chooseMCQ();
        setUserNameToChosenMCQ(chosenMcq, userNameInput);
        chooseMcqToRun(chosenMcq);
    }

    public void showMCQLists() {
        System.out.println("Choose your Multiple Choice Question set. The options are: ");
        for (int i = 0; i < this.objectsToRun.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + this.objectsToRun.get(i).getMcqName());
        }
    }

    public void chooseMCQ() {
        chosenMcq = 0;
        boolean isNumber;
        do {
            do {
                System.out.print(">> Input number: ");
                if (input.hasNextInt()) {
                    chosenMcq = input.nextInt();
                    isNumber = true;
                } else {
                    System.out.println("   Your input is invalid!");
                    isNumber = false;
                    input.next();
                }
            } while (!(isNumber));
            if (chosenMcq > this.objectsToRun.size()) {
                System.out.println("   Your input is out of range!");
            }
        } while (chosenMcq > this.objectsToRun.size());
    }

    private void chooseMcqToRun(int orderOfMCQ) {
        this.objectsToRun.get(orderOfMCQ - 1).doMCQ();
    }

    private void setUserNameToChosenMCQ(int orderOfMCQ, String userNameInput) {
        this.objectsToRun.get(orderOfMCQ - 1).setUserName(userNameInput);
    }
    private void setUserName(String userNameInput) {
        this.userName = userNameInput;
    }

    private String getUserName() {
        return this.userName;
    }
}
