package sh.alex.onlineTesting.model.tests;


import lombok.Getter;


import java.util.*;


@Getter
public class Test {

    private Map<Question, List<Answer>> questions;

    public Test(Map<Question, List<Answer>> questions) {
        this.questions = questions;
    }

    //Конструктор-рыба
    public Test() {

        this.questions = new HashMap<Question, List<Answer>>();



        questions.put(new Question("Где плавает рыба?"),

                        new ArrayList<Answer>(Arrays.asList(
                                new Answer("В грунте", false),
                                new Answer("По воздуху", false),
                                new Answer("В воде", true),
                                new Answer("Рыба не плавает", false)
                        )));

        questions.put(
                new Question("Какого цвета солнце?"),
                        new ArrayList<Answer>(Arrays.asList(
                                new Answer("Красное", false),
                                new Answer("Жёлтое", true),
                                new Answer("Синее", false),
                                new Answer("Солнце не имеет цвета, поскольку является объектом излучения волн всех диапозонов", true)
                        )));
    }

    public void addQuestion(Question question, List<Answer> answers) {
        this.questions.put(question, answers);
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
