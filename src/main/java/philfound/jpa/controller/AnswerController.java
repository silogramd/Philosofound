package philfound.jpa.controller;
import philfound.jpa.model.User;
import philfound.jpa.exception.ResourceNotFoundException;
import philfound.jpa.model.Answer;
import philfound.jpa.repository.AnswerRepository;
import philfound.jpa.repository.QuestionRepository;
import philfound.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/answers")
    public Page<Answer> getAllAnswers(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    @GetMapping("/questions/{questionId}/answers")
    public Page<Answer> getAllAnswersByQuestionId(@PathVariable(value = "questionId") Long questionId,
                                                Pageable pageable) {
        return answerRepository.findByQuestionId(questionId, pageable);
    }
    @GetMapping("/users/{userId}/answers")
    public Page<Answer> getAllAnswersByUserId(@PathVariable(value = "userId") Long userId,
                                                Pageable pageable) {
        return answerRepository.findByUser(userRepository.findById(userId).orElse(new User()), pageable);
    }

    @PostMapping("/questions/{questionId}/answers/{userId}")
    public Answer createAnswer(@PathVariable(value = "questionId") Long questionId,
                               @PathVariable(value = "userId") Long userId,
                                 @Valid @RequestBody Answer answer) {
        return questionRepository.findById(questionId).map(question -> {
          answer.setQuestion(question);
          return userRepository.findById(userId).map(user -> {
            answer.setUser(user);
            return answerRepository.save(answer);
          }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));

        }).orElseThrow(() -> new ResourceNotFoundException("QuestionId " + questionId + " not found"));
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public Answer updateAnswer(@PathVariable(value = "questionId") Long questionId,
                                 @PathVariable(value = "answerId") Long answerId,
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
    public ResponseEntity<?> deleteAnswer(@PathVariable(value = "questionId") Long questionId,
                              @PathVariable(value = "answerId") Long answerId) {
        return answerRepository.findByIdAndQuestionId(answerId, questionId).map(answer -> {
            answerRepository.delete(answer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId + " and questionId " + questionId));
    }
}
