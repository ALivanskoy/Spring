package sh.alex.onlineTesting.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.alex.onlineTesting.model.entities.tests.Answer;
import sh.alex.onlineTesting.model.entities.tests.Question;
import sh.alex.onlineTesting.model.entities.tests.factory.AnswerFactory;
import sh.alex.onlineTesting.model.entities.tests.factory.QuestionFactory;
import sh.alex.onlineTesting.model.services.AnswerService;
import sh.alex.onlineTesting.model.services.QuestionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/qa")
@RequiredArgsConstructor
public class QAController {

    @Autowired
    private final QuestionService questionService;

    @Autowired
    private final AnswerService answerService;

    @Autowired
    private final QuestionFactory questionFactory;

    @Autowired
    private final AnswerFactory answerFactory;


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
        Long questionId;
        try {
            Question createdQuestion = questionFactory.createQuestion(question.getText());
            questionId = questionService.create(createdQuestion).getId();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(questionId, HttpStatus.OK);
    }

    @PostMapping("/question/{questionId}")
    public ResponseEntity<Long> postAnswers(@PathVariable Long questionId, @RequestBody List<Answer> answers) {
        try {
            answers.forEach(answer -> {
                Answer createdAnswer = answerFactory.createAnswer(answer.getText(), answer.getCorrect());
                answerService.create(createdAnswer, questionId);
            });
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
