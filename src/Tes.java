import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tes {
    public static void main(String[] args) {
        String array1 = "1";
        String[] array2 = {"1"};
        int number = 1;

        List<String> list = new ArrayList<String>();
        list.add("1");

        System.out.println(list);
        System.out.println(Arrays.toString(array2));

        System.out.println(list.containsAll(Arrays.asList(array2)));
//        if (Arrays.asList(list).containsAll(Arrays.asList(array2))) {
//            System.out.println("Yes");
//        } else {
//            System.out.println("No");
//        }

    }
}
