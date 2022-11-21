import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class MCQWithText extends MCQ {
    private List<Integer> MCQType;

    public MCQWithText(String mcqName, String filePath) {
        super(mcqName, filePath);
    }

    public void doMCQ() {
        String delimiter = ",";
        String currentLine;

        try {
            FileReader fr = new FileReader(this.getFilePath());
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                questionLine = currentLine.split(delimiter);
                questionsCounter++;

                if (getMCQType().equals(0)) {
                    super.printQuestion();
                    super.setSingleAnswerValidation();
                    super.setUserAnswer();
                    super.checkUserAnswer();
                } else if (getMCQType().equals(0)) {
                    // Does the Text MCQ Type
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getStatus();
    }
}
