package philfound.jpa.controller;

import org.springframework.data.domain.Page;
import philfound.jpa.model.Question;
import philfound.jpa.repository.AnswerRepository;
import philfound.jpa.repository.QuestionRepository;
import philfound.jpa.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import philfound.jpa.exception.ResourceNotFoundException;

import javax.validation.Valid;

@RestController
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/questions")
    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @GetMapping("/users/{userId}/questions")
    public Page<Question> getAllQuestionsByUserId(@PathVariable(value = "userId") Long userId,
                                                Pageable pageable) {
        return questionsRepository.findByUser(userRepository.findById(userId), pageable);
    }

    @PostMapping("/questions/{userId}")
    public Question createQuestion(@PathVariable(value = "userId") Long userId, @Valid @RequestBody Question question) {
        return userRepository.findById(userId).map(user -> {
          question.setUser(user);
          return questionRepository.save(question);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/questions/{questionId}")
    public Question updateQuestion(@PathVariable Long questionId, @Valid @RequestBody Question questionRequest) {
        return questionRepository.findById(questionId).map(question -> {
            question.setQuestion(questionRequest.getQuestion());
            return questionRepository.save(question);

        }).orElseThrow(() -> new ResourceNotFoundException("QuestionId " + questionId + " not found"));
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionRepository.findById(questionId).map(question -> {
            questionRepository.delete(question);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + questionId + " not found"));
    }

}
