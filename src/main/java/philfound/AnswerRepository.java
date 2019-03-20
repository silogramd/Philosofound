package philfound;

import org.springframework.data.repository.CrudRepository;

import philfound.Answer;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    List<Answer> findByAnswer(String answer);
}
