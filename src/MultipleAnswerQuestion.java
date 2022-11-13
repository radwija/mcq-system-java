public class MultipleAnswerQuestion extends Question {
    String prompt;

    String answer2;
    String answer3;
    String answer4;
    String answer5;


    // Default answer that has one answer
    public MultipleAnswerQuestion(String prompt, String mainAnswer) {
        super(prompt, mainAnswer);
    }

    // Multiple-answer question
    public MultipleAnswerQuestion(String prompt, String mainAnswer, String answer2) {
        super(prompt, mainAnswer);
        this.answer2 = answer2;
    }

    public MultipleAnswerQuestion(String prompt, String mainAnswer, String answer2, String answer3) {
        super(prompt, mainAnswer);
        this.answer2 = answer2;
        this.answer3 = answer3;
    }

    public MultipleAnswerQuestion(String prompt, String mainAnswer, String answer2, String answer3, String answer4) {
        super(prompt, mainAnswer);
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    public MultipleAnswerQuestion(String prompt, String mainAnswer, String answer2, String answer3, String answer4, String answer5) {
        super(prompt, mainAnswer);
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
    }

    public void checkAnswer(String answerInput) {

    }
}
