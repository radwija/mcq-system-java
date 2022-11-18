import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tes {
    public static void main(String[] args) {
        String array1 = "a";
        String[] array2 = {"a", "b"};
        int number = 1;

        List<String> userInput = new ArrayList<String>();
        userInput.add("b");
        userInput.add("a");

        System.out.println("jawaban:" + Arrays.toString(array2));
        System.out.println("input: " + userInput);
        System.out.println(userInput.containsAll(Arrays.asList(array2)));


//        if (Arrays.asList(list).containsAll(Arrays.asList(array2))) {
//            System.out.println("Yes");
//        } else {
//            System.out.println("No");
//        }

    }
}
