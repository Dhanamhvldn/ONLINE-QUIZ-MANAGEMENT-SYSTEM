package org.OnlineQuizApplication.Controller;

import java.util.Map;

import org.OnlineQuizApplication.Entity.Question;
import org.OnlineQuizApplication.Entity.Quiz;
import org.OnlineQuizApplication.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ResultController {
	@Autowired
	private QuizService quizService;
	
	
	
	@PostMapping("/result-quiz")
	public String submitQuiz(@RequestParam Map<String, String> userAnswers, 
	                         @RequestParam Long quizId, 
	                         Model model, 
	                         HttpSession session) {
	    // Logic to calculate score and time taken
	    Quiz quiz = quizService.findQuizById(quizId);

	    int score = 0;
	    int questionsAttempted = 0;

	    for (Question question : quiz.getQuestions()) {
	        String userAnswer = userAnswers.get("question-" + question.getId());
	        if (userAnswer != null) {
	            questionsAttempted++;
	            if (userAnswer.equals(question.getCorrectoption())) {
	                score++;
	            }
	        }
	    }

	    long startTime = (long) session.getAttribute("startTime");
	    long endTime = System.currentTimeMillis();
	    long timeTaken = endTime - startTime;

	    long minutes = (timeTaken / 1000) / 60;
	    long seconds = (timeTaken / 1000) % 60;

	    model.addAttribute("score", score);
	    model.addAttribute("totalQuestions", quiz.getQuestions().size());
	    model.addAttribute("questionsAttempted", questionsAttempted);
	    model.addAttribute("timeTaken", minutes + "m " + seconds + "s");

	    return "result";
	}



}
