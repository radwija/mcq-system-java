import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MCQ_1> MCQSets = new ArrayList<>();
        MCQ_1 htmlBasic = new MCQ_1("HTML Basic", "src/mcq/html-basic.csv");
        MCQ_1 cssBasic = new MCQ_1("CSS Basic", "src/mcq/css-basic.csv");
        MCQ_1 javaBasic = new MCQ_1("Java Basic", "src/mcq/java-basic.csv");
        MCQSets.add(htmlBasic);
        MCQSets.add(cssBasic);
        MCQSets.add(javaBasic);

        MCQExecutor ME = new MCQExecutor(MCQSets);
        ME.executeMcq();

    }
}