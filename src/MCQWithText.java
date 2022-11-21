import java.io.BufferedReader;
import java.io.FileReader;

public class MCQWithText extends MCQ {
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

                this.doTextTypeMCQ(getMCQType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getStatus();
    }

    private void doTextTypeMCQ(int type) {
        switch (type) {
            case 0:
                super.printQuestion();
                super.setSingleAnswerValidation();
                super.setUserAnswer();
                super.checkUserAnswer();
                break;
            case 1:
                System.out.println("\n~ Question " + this.getQuestionsCounter() + " ~" + "\n*** Text Type MCQ works ***");
                // this.printQuestion();
                // this.setSingleAnswerValidation();
                // this.setUserAnswer();
                // this.checkUserAnswer();
                break;
        }
    }
}
