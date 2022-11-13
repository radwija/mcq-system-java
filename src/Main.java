import java.awt.desktop.QuitEvent;

public class Main {
    public static void main(String[] args) {
        String q1 = "Warna klorofil adalah ... \n (a) Hijau\n (b) Merah \n (c) Biru";
        String q2 = "Warna klorofil adalah ... \n (a) Hijau\n (b) Merah \n (c) Biru";
        String q3 = "Warna klorofil adalah ... \n (a) Hijau\n (b) Merah \n (c) Biru";
        Question[] question = {
                new Question(q1, "a"),
                new Question(q2, "a"),
                new Question(q2, "a"),
                new MultipleAnswerQuestion(q3, "a", "c")
        };

        System.out.println(question[0].getPrompt());

    }
}