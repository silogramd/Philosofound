package philfound;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.*;
import javax.persistence.*;

@Data

@Entity // This tells Hibernate to make a table out of this class
public class Answer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String answer;
    
    @ManyToOne
    @JoinColumn
    private int question;
    
    public Answer() {

    }

    public Answer(String answer) {
        this.answer = answer;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

    public void setQuestion(Question question) {
        this.question = question.getId();
    }
}

