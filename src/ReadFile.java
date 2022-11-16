import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ReadFile {
    public static void main(String[] args) {
        String file = "src/cities.csv";
        String delimiter = ",";
        String line;
        List<List<String>> lines = new ArrayList();
        try (Scanner s = new Scanner(new File(file))) {
            while (s.hasNext()) {
                line = s.next();
                List values = Arrays.asList(line.split(delimiter));
                lines.add(values);
                System.out.println(values.get(0));
            }
            lines.forEach(l -> System.out.println(l));
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}