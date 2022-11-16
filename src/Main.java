import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Reading CSV file
        String path = "src/question.csv";
        List<String> lines = new ArrayList<>();
        String line = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                lines.add(value[0]);
                lines.add(value[2]);
                System.out.println(value[1]);
            }
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ArrayIndexOutOfBoundsException ignored){}

//        if (lines.get(0) == "d") {
//            System.out.println("Benar");
//        } else {
//            System.out.println("Salah");
//        }
//        for (int i = 0; i < lines.size(); i++) {
//            System.out.println(lines.get(i));
//        }


    }
}