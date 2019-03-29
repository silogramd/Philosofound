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
    public Page<Comment> getAllAnswersByQuestionId(@PathVariable (value = "questionId") Long questionId,
                                                Pageable pageable) {
        return answerRepository.findByQuestionId(questionId, pageable);
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer createAnswer(@PathVariable (value = "questionId") Long questionId,
                                 @Valid @RequestBody Answer answer) {
        return questionRepository.findById(questionId).map(question -> {
            answer.setQuestion(question);
            return answerRepository.save(answer);
        }).orElseThrow(() -> new ResourceNotFoundException("QuestionId " + questionId + " not found"));
    }

    @PutMapping("/questions/{questionnId}/answers/{answerId}")
    public Answer updateAnswer(@PathVariable (value = "questionId") Long questionId,
                                 @PathVariable (value = "answerId") Long answerId,
                                 @Valid @RequestBody Answer answerRequest) {
        if(!answerRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("QuestionId " + questionId + " not found");
        }

        return answerRepository.findById(answerId).map(answer -> {
            answer.setText(answerRequest.getText());
            return answerRepository.save(answer);
        }).orElseThrow(() -> new ResourceNotFoundException("AnswerId " + answerId + "not found"));
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable (value = "questionId") Long questionId,
                              @PathVariable (value = "answerId") Long answerId) {
        return answerRepository.findByIdAndQuestionId(answerId, questionId).map(answer -> {
            answerRepository.delete(answer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId + " and questionId " + questionId));
    }
}
