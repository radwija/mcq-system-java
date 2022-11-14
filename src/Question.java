public class Question {
    String prompt;
    String answer;

    String margin = "   ";

    public Question(String prompt, String answer) {
        this.prompt = prompt;
        this.answer = answer;
    }

    public String getPrompt() {
        return prompt;
    }
}
