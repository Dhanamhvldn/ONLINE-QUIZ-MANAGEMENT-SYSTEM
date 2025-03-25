package org.OnlineQuizApplication.Repository;

import org.OnlineQuizApplication.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
	 @Query("SELECT q FROM Quiz q JOIN FETCH q.questions WHERE q.id = :quizId")
	    Quiz findQuizWithQuestions(@Param("quizId") Long quizId);

}
