import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<mcq> mcqSet = new ArrayList<mcq>();
        mcq javaBasic = new mcq("Java Basic", "src/java-basic.csv",6);
        mcq dummy = new mcq("Java Basic", "src/question.csv",6);
        mcqSet.add(javaBasic);
        mcqSet.add(dummy);

        mcqExecutor mcqRunner = new mcqExecutor(mcqSet);
        mcqRunner.runObject(1);
    }
}