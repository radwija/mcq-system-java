import java.util.ArrayList;
import java.util.List;

public class RunMCQ {
    private List<MCQ> objectsToRun = new ArrayList<MCQ>();

    public RunMCQ(List<MCQ> objectsToRun) {
        this.objectsToRun = objectsToRun;
    }

    public void runObject(int orderOfMCQ) {
        this.objectsToRun.get(orderOfMCQ - 1).doMCQ();
    }


}
