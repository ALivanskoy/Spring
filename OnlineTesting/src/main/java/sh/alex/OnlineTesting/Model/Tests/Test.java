package sh.alex.OnlineTesting.Model.Tests;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Test {

    private List<Question> questions;

    public Test(List<Question> questions) {
        this.questions = questions;
    }

    //Конструктор-рыба
    public Test() {

        questions = new ArrayList<>();

        questions.add(
                new Question("Где плавает рыба?",
                        new ArrayList<Answer>(Arrays.asList(
                                new Answer("в грунте", false),
                                new Answer("по воздуху", false),
                                new Answer("в воде", true),
                                new Answer("рыба не плавает", false)
                        ))));

        questions.add(
                new Question("Какого цвета солнце?",
                        new ArrayList<Answer>(Arrays.asList(
                                new Answer("Красное", false),
                                new Answer("Жёлтое", true),
                                new Answer("Синее", false),
                                new Answer("Солнце не имеет цвета, поскольку является объектом излучения волн всех диапозонов", true)
                        ))));
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void addQuestion(String questionText, List<Answer> answers) {
        this.questions.add(
                new Question( questionText, answers));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(questions, test.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questions);
    }

    @Override
    public String toString() {
        return questions.toString();
    }

}
