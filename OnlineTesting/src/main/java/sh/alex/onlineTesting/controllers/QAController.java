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

    @GetMapping("/question/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {

        try {
            return new ResponseEntity<>(questionService.getById(questionId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/answersfor/{questionId}")
    public ResponseEntity<List<Answer>> getAnswersByQuestionId(@PathVariable Long questionId) {

        try {
            return new ResponseEntity<>(answerService.getByQuestionId(questionId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/question")
    public ResponseEntity<Long> postQuestion(@RequestBody Question question) {

        final Long questionId;

        try {
            questionId = questionService.create(question).getId();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(questionId, HttpStatus.OK);
    }

    @PostMapping("/question/{questionId}")
    public ResponseEntity<Long> postAnswers(@PathVariable Long questionId, @RequestBody List<Answer> answers) {

        try {
            answers.forEach(answer -> answerService.create(answer, questionId));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/question/{questionId}")
    public ResponseEntity<Question> putQuestion(@PathVariable Long questionId, @RequestBody Question question) {

        try {
            questionService.update(questionId, question);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/answer/{answerId}")
    public ResponseEntity<Question> putAnswer(@RequestBody Long answerId, Answer answer) {


        try {
            answerService.update(answerId, answer);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Long id) {
        try {
            answerService.getByQuestionId(id).forEach(answer -> answerService.delete(answer.getId()));
            questionService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity<Answer> deleteAnswer(@PathVariable Long id) {
        try {
            answerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
