import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<mcq> mcqSets = new ArrayList<mcq>();
        mcq javaBasic = new mcq("Java Basic", "src/java-basic.csv",6);
        mcq dummy = new mcq("Dummy", "src/question.csv",6);
        mcqSets.add(javaBasic);
        mcqSets.add(dummy);

        mcqExecutor ME = new mcqExecutor(mcqSets);
        ME.executeMcq();
    }
}