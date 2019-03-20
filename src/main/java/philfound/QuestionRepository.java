package philfound;

import org.springframework.data.repository.CrudRepository;

import philfound.Question;

import java.util.List;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    List<Question> findByQuestion(String question);
}
