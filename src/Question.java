import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Question {
    public String filePath;
    public int amountOfField;

//    public String[][] questionData = this.getQuestionData();

    public Question(String filePath, int amountOfField) {
        this.filePath = filePath;
        this.amountOfField = amountOfField;
    }
    public String[][] getQuestionData() {
        List<String> recordList = new ArrayList<String>();

        String delimiter = ",";
        String currentLine;

        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                recordList.add(currentLine);
            }
            int recordCount = recordList.size();

            String arrayToReturn[][] = new String[recordCount][this.amountOfField];
            String[] data;

            for (int i = 0; i < recordCount; i++) {
                data = recordList.get(i).split(delimiter);
                for (int j = 0; j < data.length; j++) {
                    arrayToReturn[i][j] = data[j];
                }
            }
            return arrayToReturn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public static void main(String[] args) {
        String filePath = "src/question.csv";
        Question q = new Question(filePath, 6);
        String[][] data = q.getQuestionData();

        if (data[0][0] == "question") {
            System.out.println("Benar");
        } else {
            System.out.println("Salah");
        }
    }
}
