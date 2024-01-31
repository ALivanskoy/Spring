package sh.alex.OnlineTesting.Model.Tests;

import java.util.List;

public class Question {

    //Текст вопроса
    private final String text;

    //Список ответов
    private final List<Answer> answers;


    public Question(String text, List<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }



    @Override
    public String toString() {
        return text +"\n"+answers+"\n";
    }
}
