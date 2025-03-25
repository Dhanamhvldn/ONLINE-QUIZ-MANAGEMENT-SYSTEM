package org.OnlineQuizApplication.Repository;

import java.util.List;

import org.OnlineQuizApplication.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>{

	List<Question> findByQuizId(Long quizId);

}
