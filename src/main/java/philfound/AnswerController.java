package philfound.jpa.controller;

import philfound.jpa.exception.ResourceNotFoundException;
import philfound.jpa.model.Answer;
import philfound.jpa.repository.AnswerRepository;
import philfound.jpa.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions/{questionId}/answers")
    public Page<Comment> getAllAnswersByQuestionID(@PathVariable (value = "questionID") Long questionID,
                                                Pageable pageable) {
        return questionRepository.findByQuestionID(questionID, pageable);
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer createAnswers(@PathVariable (value = "quesionId") Long questionId,
                                 @Valid @RequestBody Answer answer) {
        return questionRepository.findById(questionID).map(question -> {
            answer.setQuestion(question);
            return answerRepository.save(answer);
        }).orElseThrow(() -> new ResourceNotFoundException("QuestionID " + questionId + " not found"));
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public Answer updateAnswer(@PathVariable (value = "questionId") Long question,
                                 @PathVariable (value = "answerID") Long answerId,
                                 @Valid @RequestBody Answer answerRequest) {
        if(!questionRepository.existsById(questionID)) {
            throw new ResourceNotFoundException("QuestionID " + questionID + " not found");
        }

        return answerRepository.findById(answerID).map(answer -> {
            answer.setText(answerRequest.getText());
            return answerRepository.save(answer);
        }).orElseThrow(() -> new ResourceNotFoundException("AnswerID " + answerId + "not found"));
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable (value = "answerID") Long answerID,
                              @PathVariable (value = "answerID") Long answerID) {
        return answerRepository.findByIdAndQuestionId(answerId, questionId).map(comment -> {
            questionRepository.delete(answer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerID + " and questionID " + setQuestionId));
    }
}
