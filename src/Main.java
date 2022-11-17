import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MCQ> mcqSet = new ArrayList<MCQ>();
        MCQ javaBasic = new MCQ("Java Basic", "src/java-basic.csv",6);
        MCQ dummy = new MCQ("Java Basic", "src/question.csv",6);
        mcqSet.add(javaBasic);
        mcqSet.add(dummy);

        RunMCQ mcqRunner = new RunMCQ(mcqSet);
        mcqRunner.runObject(1);
    }
}