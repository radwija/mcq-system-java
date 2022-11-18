import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MCQExecutor {
    private List<MCQ> objectsToRun;
    private String name;

    public MCQExecutor(List<MCQ> objectsToRun) {
        this.objectsToRun = objectsToRun;
    }

    public void setName(String inputName) {
        this.name = inputName;
    }

    public String getName() {
        return this.name;
    }

    public void chooseMcq(int orderOfMCQ) {
        this.objectsToRun.get(orderOfMCQ - 1).doMCQ();
    }

    public void setNameToChosenMCQ(int orderOfMCQ, String nameInput) {
        this.objectsToRun.get(orderOfMCQ - 1).setName(nameInput);
    }

    public String getChosenMcqName(int orderOfMCQ) {
        return this.objectsToRun.get(orderOfMCQ - 1).getMcqSetName();
    }

    public void executeMcq() {
        Scanner input = new Scanner(System.in);
        System.out.print("Hi, how can we address you?\n>> Enter your name: ");
        String nameInput = input.nextLine();
        this.setName(nameInput);
        System.out.println("\nWelcome to our MCQ on IT, " + this.getName() + ":)");
        System.out.println("Choose your Multiple Choice Question Set. The Options are: ");
        for (int i = 0; i < this.objectsToRun.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + this.objectsToRun.get(i).getMcqSetName());
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
                    System.out.println("Your input is invalid!");
                    isNumber = false;
                    input.next();
                }
            } while (!(isNumber));
            if (chosenMcq > this.objectsToRun.size()) {
                System.out.println("Your input is out of range!");
            }
        } while (chosenMcq > this.objectsToRun.size());
        System.out.println("\n*** " + this.getChosenMcqName(chosenMcq) + " ***");
        System.out.println("Good luck, " + this.getName() + "!");
        this.setNameToChosenMCQ(chosenMcq, nameInput);
        this.chooseMcq(chosenMcq);
    }
}
