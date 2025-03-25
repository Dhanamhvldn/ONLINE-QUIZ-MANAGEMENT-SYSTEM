package org.OnlineQuizApplication.Service;

import java.util.List;
import java.util.Optional;

import org.OnlineQuizApplication.Entity.Question;
import org.OnlineQuizApplication.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	public Question saveQuestion(Question question) {
		return questionRepository.save(question);
		
	}
	
	public Question updateQuestion(Question question) {
		return questionRepository.save(question);
	}
	public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
	public Optional<Question> getQuestionById(Long id) {
		// TODO Auto-generated method stub
		return questionRepository.findById(id);
	}
	public void deleteQuestion(Long id) {
        questionRepository.deleteById(id); // Delete question by ID
    }

	
	 public List<Question> getQuestionsByQuizId(Long quizId) {
	        return questionRepository.findByQuizId(quizId); // Fetch questions by quiz ID
	    }

	
	
}
