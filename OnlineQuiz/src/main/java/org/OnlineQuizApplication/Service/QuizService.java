package org.OnlineQuizApplication.Service;

import java.util.List;

import org.OnlineQuizApplication.Entity.Question;
import org.OnlineQuizApplication.Entity.Quiz;
import org.OnlineQuizApplication.Repository.QuestionRepository;
import org.OnlineQuizApplication.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
	@Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // Create a new quiz
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    
    public Quiz findQuizById(Long quizId) {
		// TODO Auto-generated method stub
		return quizRepository.getById(quizId);
	}
	
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll(); // Fetch all quizzes
    }
    public Quiz findQuizById1(Long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizId));
    }


	
}
