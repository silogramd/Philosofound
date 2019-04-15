package philfound.jpa.controller;

import org.springframework.data.domain.Page;

import philfound.jpa.model.Answer;
import philfound.jpa.model.Question;
import philfound.jpa.repository.AnswerRepository;
import philfound.jpa.repository.QuestionRepository;
import philfound.jpa.repository.UserRepository;
import philfound.jpa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/questions")
    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @GetMapping("/users/{userId}/questions")
    public Page<Question> getAllQuestionsByUserId(@PathVariable(value = "userId") Long userId,
                                                Pageable pageable) {
        return questionRepository.findByUser(userRepository.findById(userId).orElse(new User()), pageable);
    }

    @PostMapping("/questions/{userId}")
    public Question createQuestion(@PathVariable(value = "userId") Long userId, @Valid @RequestBody Question question) {
        return userRepository.findById(userId).map(user -> {
          question.setUser(user);
          return questionRepository.save(question);
        }).orElseThrow(() -> new IllegalStateException("UserId " + userId + " not found"));
    }

    @PutMapping("/questions/{questionId}")
    public Question updateQuestion(@PathVariable Long questionId, @Valid @RequestBody Question questionRequest) {
        return questionRepository.findById(questionId).map(question -> {
            question.setQuestion(questionRequest.getQuestion());
            return questionRepository.save(question);

        }).orElseThrow(() -> new IllegalStateException("QuestionId " + questionId + " not found"));
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionRepository.findById(questionId).map(question -> {
            questionRepository.delete(question);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalStateException("Can't find the question!");
    }

    @GetMapping("/questions/{userId}/next_question")
    public Question getNextQuestion(@PathVariable Long userId) {
        ArrayList<Answer> answered = userRepository.findById(userId).get().getVotes();
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.addAll(questionRepository.findAll());

        for(Question q : questions) {
            for(Answer a : answered) {
                if(!(a.getQuestion() == q)) {
                    return q;
                }
            }
        }

        return null;
    }

}
