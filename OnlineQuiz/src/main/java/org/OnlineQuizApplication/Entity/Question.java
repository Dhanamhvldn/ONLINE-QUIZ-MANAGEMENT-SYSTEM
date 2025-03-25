package org.OnlineQuizApplication.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	long id;
	 @Column(nullable = false)
	private	String question;
	private	String optionA;
	private	String  optionB;
	private	String  optionC;
	private	String  optionD;
	  @Column(nullable = false)
	    private String correctoption;

	    @ManyToOne
	    @JoinColumn(name = "quiz_id", nullable = false)
	    private Quiz quiz;


	public Quiz getQuiz() {
		return quiz;
	}
	public String getCorrectoption() {
		return correctoption;
	}
	public void setCorrectoption(String correctoption) {
		this.correctoption = correctoption;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	
	
		

}
