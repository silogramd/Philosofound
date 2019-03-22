package philfound;

import org.springframework.data.repository.CrudRepository;

import philfound.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    Page<Answer> findByAnswer(String answer);
    Page<Answer> findByQuestionId(int id, int postId);
}
