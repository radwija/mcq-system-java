import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MCQ> MCQSets = new ArrayList<>();
        MCQ htmlBasic = new MCQ("HTML Basic", "src/mcq/html-basic.csv");
        MCQ cssBasic = new MCQ("CSS Basic", "src/mcq/css-basic.csv");
        MCQ javaBasic = new MCQ("Java Basic", "src/mcq/java-basic.csv");
        MCQ dummyChildObject = new MCQWithText("HTML Basic (Dummy MCQ Text answer)", "src/mcq/html-basic.csv");
        MCQSets.add(htmlBasic);
        MCQSets.add(cssBasic);
        MCQSets.add(javaBasic);
        MCQSets.add(dummyChildObject);

        MCQExecutor ME = new MCQExecutor(MCQSets);
        ME.executeMcq();

    }
}