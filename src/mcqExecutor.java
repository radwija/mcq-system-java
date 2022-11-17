import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mcqExecutor {
    private List<mcq> objectsToRun = new ArrayList<mcq>();

    public mcqExecutor(List<mcq> objectsToRun) {
        this.objectsToRun = objectsToRun;
    }

    public void chooseMcq(int orderOfMCQ) {
        this.objectsToRun.get(orderOfMCQ - 1).doMCQ();
    }

    public void executeMcq() {
        Scanner input = new Scanner(System.in);
        System.out.print("Hello, how can we address you?\nEnter your name: ");
        String nameInput = input.next();
        System.out.println("\nWelcome to our MCQ on IT, " + nameInput + ":)");
        System.out.println("Choose your Multiple Choice Question Set. The Options are: ");
        for (int i = 0; i < this.objectsToRun.size(); i++) {
            System.out.println((i + 1) + ". " + this.objectsToRun.get(i).getMcqSetName());
        }
        System.out.print("Input number: ");
        int chosenMcq = input.nextInt();
        System.out.println("\nGood luck, " + nameInput + ".");
        this.chooseMcq(chosenMcq);
    }
}
