import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MCQ> MCQSets = new ArrayList<>();
        MCQ htmlBasic = new MCQ("HTML Basic", "src/mcq/html-basic.csv",7);
        MCQ cssBasic = new MCQ("CSS Basic", "src/mcq/css-basic.csv",7);
        MCQ javaBasic = new MCQ("Java Basic", "src/mcq/java-basic.csv",7);
        MCQSets.add(htmlBasic);
        MCQSets.add(cssBasic);
        MCQSets.add(javaBasic);

        MCQExecutor ME = new MCQExecutor(MCQSets);
        ME.executeMcq();
    }
}