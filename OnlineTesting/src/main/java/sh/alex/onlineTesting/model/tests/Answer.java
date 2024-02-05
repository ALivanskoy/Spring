package sh.alex.onlineTesting.model.tests;


import lombok.Getter;

@Getter
public class Answer {

    //Текст ответа
    final private String answer;

    //Является ли ответ верным
    final private Boolean isCorrect;

    public Answer(String answer, Boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    @Override
    public String toString() {
        return answer;
    }
}
