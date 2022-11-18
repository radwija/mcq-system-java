import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MCQ> MCQSets = new ArrayList<MCQ>();
        MCQ htmlBasic = new MCQ("HTML Basic", "src/mcq/html-basic.csv",7);
        MCQ javaBasic = new MCQ("Java Basic", "src/mcq/java-basic.csv",7);
        MCQSets.add(htmlBasic);
        MCQSets.add(javaBasic);


        MCQExecutor ME = new MCQExecutor(MCQSets);
        ME.executeMcq();
    }
}