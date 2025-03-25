package org.OnlineQuizApplication.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.OnlineQuizApplication.Entity.Question;
import org.OnlineQuizApplication.Entity.Quiz;
import org.OnlineQuizApplication.Repository.QuestionRepository;
import org.OnlineQuizApplication.Service.QuestionService;
import org.OnlineQuizApplication.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {
	 @Autowired
private QuestionService questionService;
@Autowired
private QuestionRepository questionRepository;
@Autowired
private QuizService quizService;
//Show the Add Question form
@GetMapping("/add-question")
public String showAddQuestionPage(Model model) {
 model.addAttribute("quizList", quizService.getAllQuizzes()); // Pass list of quizzes for selection
 model.addAttribute("question", new Question()); // Add a blank Question object
 return "addQuestion"; // Display addQuestion.html template
}

//Handle form submission to save the question
@PostMapping("/save-question")
public String createQuestion(@ModelAttribute("question") Question question) {
 System.out.println("Received question: " + question);

 if (question.getQuiz() == null || question.getQuiz().getId() == 0) {
     System.out.println("Quiz is null or invalid! Cannot save question.");
     return "redirect:/add-question";
 }

 questionService.saveQuestion(question); // Save the question to the database
 return "redirect:/manage-questions"; // Redirect to manage questions page
}


@GetMapping("/manage-questions")
public String manageQuestions(Model model) {
    List<Question> questions = questionService.getAllQuestions(); // Fetch all questions
    if (questions == null) {
        questions = new ArrayList<>();
    }
    model.addAttribute("questions", questions); // Add questions to the model
    return "managequestion"; // Thymeleaf template name
}


@GetMapping("/edit-question/{id}")
public String editQuestion(@PathVariable Long id, Model model) {
    Optional<Question> question = questionService.getQuestionById(id);
    if (question.isPresent()) {
        model.addAttribute("question", question.get());
        return "edit-question"; // Must match the template name
    } else {
        return "redirect:/manage-questions";
    }
}

@PostMapping("/update-question")
public String updateQuestion(@ModelAttribute Question question) {
    questionService.saveQuestion(question); // Save the updated question
    return "redirect:/manage-questions"; // Redirect back to manage questions page
}


// Delete a Question
@GetMapping("/delete-question/{id}")
public String deleteQuestion(@PathVariable Long id) {
    questionService.deleteQuestion(id); // Delete question by ID
    return "redirect:/manage-questions"; // Redirect back to manage questions
}


}
