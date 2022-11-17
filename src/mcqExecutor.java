import java.util.ArrayList;
import java.util.List;

public class mcqExecutor {
    private List<mcq> objectsToRun = new ArrayList<mcq>();

    public mcqExecutor(List<mcq> objectsToRun) {
        this.objectsToRun = objectsToRun;
    }

    public void runObject(int orderOfMCQ) {
        this.objectsToRun.get(orderOfMCQ - 1).doMCQ();
    }


}
