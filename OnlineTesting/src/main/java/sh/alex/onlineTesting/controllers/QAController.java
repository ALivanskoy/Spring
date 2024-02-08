package sh.alex.onlineTesting.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.alex.onlineTesting.model.entities.tests.Answer;
import sh.alex.onlineTesting.model.entities.tests.Question;
import sh.alex.onlineTesting.model.services.AnswerService;
import sh.alex.onlineTesting.model.services.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/qa")
@RequiredArgsConstructor
public class QAController {

    @Autowired
    private final QuestionService questionService;

    @Autowired
    private final AnswerService answerService;


    @GetMapping
    public ResponseEntity<List<Question>> getQuestions() {

        List<Question> questions = questionService.getAll();

        if (!questions.isEmpty()) return new ResponseEntity<>(questions, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/${questionId}")
    public ResponseEntity<List<Answer>> getAnswersById(@PathVariable Long questionId) {

        List<Answer> answers = answerService.getByQuestionId(questionId);

        if (!answers.isEmpty()) return new ResponseEntity<>(answers, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/question")
    public ResponseEntity<Long> postQuestion(@RequestBody Question question, @RequestBody List<Answer> answers) {

        final Long questionId;

        try {
            questionId = questionService.create(question).getId();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(questionId, HttpStatus.OK);
    }

    @PostMapping("/answers/${questionId}")
    public ResponseEntity<Long> postQuestion(@PathVariable Long questionId, @RequestBody List<Answer> answers) {

        try {
            answers.forEach(answer -> answerService.create(answer, questionId));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Question> putQuestion(@RequestBody Long questionId, @RequestBody Question question, @RequestBody List<Answer> answers) {

        try {
            questionService.update(questionId, question);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //Здесь идёт полная замена ответов, путём удаления старых и добавления новых, поскольку кол-во ответов может не совпадать
        try {
            answerService.getByQuestionId(questionId).forEach(answer -> answerService.delete(answer.getId()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            answers.forEach(answer -> answerService.create(answer, questionId));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Long id) {
        try {
            answerService.getByQuestionId(id).forEach(answer -> answerService.delete(answer.getId()));
            questionService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
