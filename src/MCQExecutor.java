import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MCQExecutor {
    private List<MCQ> objectsToRun;
    private String userName;

    public MCQExecutor(List<MCQ> objectsToRun) {
        this.objectsToRun = objectsToRun;
    }

    private void setUserName(String nameInput) {
        this.userName = nameInput;
    }

    private String getUserName() {
        return this.userName;
    }

    private void chooseMcqToRun(int orderOfMCQ) {
        this.objectsToRun.get(orderOfMCQ - 1).doMCQ();
    }

    private void setUserNameToChosenMCQ(int orderOfMCQ, String nameInput) {
        this.objectsToRun.get(orderOfMCQ - 1).setUserName(nameInput);
    }

    private String getChosenMcqName(int orderOfMCQ) {
        return this.objectsToRun.get(orderOfMCQ - 1).getMcqName();
    }

    public void executeMcq() {
        Scanner input = new Scanner(System.in);
        System.out.print("Hi, how can we address you?\n>> Enter your name: ");
        String nameInput = input.nextLine();
        this.setUserName(nameInput);
        System.out.println("\nWelcome to our MCQ on IT, " + this.getUserName() + ":)");
        System.out.println("Choose your Multiple Choice Question set. The options are: ");
        for (int i = 0; i < this.objectsToRun.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + this.objectsToRun.get(i).getMcqName());
        }
        int chosenMcq = 0;
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
        System.out.println("\n*** " + this.getChosenMcqName(chosenMcq) + " ***");
        System.out.println("Good luck, " + this.getUserName() + "!");
        this.setUserNameToChosenMCQ(chosenMcq, nameInput);
        this.chooseMcqToRun(chosenMcq);
    }
}
