package philfound.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import philfound.jpa.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
    Page<Answer> findByAnswerId(Long postId, Pageable pageable);
    Optional<Answer> findByIdAndQuestionId(Long id, Long questionId);
}
