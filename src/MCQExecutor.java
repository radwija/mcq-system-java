import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MCQExecutor {
    private List<MCQ> objectsToRun = new ArrayList<MCQ>();
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

    public void executeMcq() {
        Scanner input = new Scanner(System.in);
        System.out.print("Hi, how can we address you?\nEnter your name: ");
        String nameInput = input.next();
        this.setName(nameInput);
        System.out.println("\nWelcome to our MCQ on IT, " + this.getName() + ":)");
        System.out.println("Choose your Multiple Choice Question Set. The Options are: ");
        for (int i = 0; i < this.objectsToRun.size(); i++) {
            System.out.println((i + 1) + ". " + this.objectsToRun.get(i).getMcqSetName());
        }
        System.out.print("Input number: ");
        int chosenMcq = input.nextInt();
        System.out.println("\nGood luck, " + this.getName() + "!");
        this.chooseMcq(chosenMcq);
    }
}
