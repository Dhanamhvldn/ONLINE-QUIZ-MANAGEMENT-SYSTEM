package org.OnlineQuizApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private	long id;
	 @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private Users user;

	    @ManyToOne
	    @JoinColumn(name = "quiz_id", nullable = false)
	    private Quiz quiz;

	    @Column(nullable = false)
private int score;
	    
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

public Users getUser() {
	return user;
}

public void setUser(Users user) {
	this.user = user;
}

public Quiz getQuiz() {
	return quiz;
}

public void setQuiz(Quiz quiz) {
	this.quiz = quiz;
}


}
