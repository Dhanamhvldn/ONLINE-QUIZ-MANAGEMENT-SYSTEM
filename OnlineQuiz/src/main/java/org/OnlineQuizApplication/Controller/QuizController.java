package org.OnlineQuizApplication.Controller;





import java.util.List;
import java.util.Map;

import org.OnlineQuizApplication.Entity.Question;
import org.OnlineQuizApplication.Entity.Quiz;
import org.OnlineQuizApplication.Entity.Users;
import org.OnlineQuizApplication.Service.QuestionService;
import org.OnlineQuizApplication.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class QuizController {
	 @Autowired
	    private QuizService quizService;
	 
	 private QuestionService questionService;
	 
	 @GetMapping("/quiz-dashboard")
	    public String showeQuizDashBoardPage() {
	        return "quiz-dashboard"; // Ensure quiz.html is in the templates folder
	    }

	 @GetMapping("/create-quiz")
	    public String showCreateQuizPage() {
	        return "createquiz"; // Ensure quiz.html is in the templates folder
	    }
	 @PostMapping("/submit-quiz")
	    public String submitQuiz(@ModelAttribute Quiz quiz) {
	        quizService.createQuiz(quiz); // Save quiz to the database
	        return "redirect:/quiz-dashboard"; // Redirect to home or quiz list page after submission
	    }
	 
	 
	 @GetMapping("/edit-quiz")
	    public String showEditQuizPage() {
	        return "editquiz"; // Ensure edit-quiz.html is in the templates folder
	    }

	    @GetMapping("/delete-quiz")
	    public String showDeleteQuizPage() {
	        return "deletequiz"; // Ensure delete-quiz.html is in the templates folder
	    }
	    
	    @GetMapping("/list-quizzes")
	    public String listQuizzes(Model model) {
	        List<Quiz> quizzes = quizService.getAllQuizzes(); // Fetch all quizzes
	        model.addAttribute("quizzes", quizzes); // Add quizzes to the model
	        return "list-quiz"; // Return the Thymeleaf template name
	    }
	    @GetMapping("/view-quiz/{quizId}")
	    public String startExam(@PathVariable Long quizId, Model model) {
	        Quiz quiz = quizService.findQuizById(quizId);
	        if (quiz == null) {
	            throw new IllegalArgumentException("Quiz not found for ID: " + quizId);
	        }
	        model.addAttribute("quiz", quiz); // Make sure 'quiz' is populated
	        return "start-exam";
	    }

	    
	   

}



