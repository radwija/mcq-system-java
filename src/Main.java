import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MCQ> MCQSets = new ArrayList<MCQ>();
        MCQ javaBasic = new MCQ("Java Basic", "src/java-basic.csv",6);
        MCQ dummy = new MCQ("Dummy", "src/question.csv",6);
        MCQSets.add(javaBasic);
        MCQSets.add(dummy);

        MCQExecutor ME = new MCQExecutor(MCQSets);
        ME.executeMcq();
    }
}