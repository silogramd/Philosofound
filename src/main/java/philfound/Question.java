package philfound;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(exclude = "answers")

@Entity // This tells Hibernate to make a table out of this class
public class Question {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String question;
    
    //@ManyToOne
    //@JoinColumn
    //private User user;

    @OneToMany(mappedBy="question", cascade = CascadeType.ALL)
    private Set<Answer> answers;

    public Question() {

    }

    public Question(String question, Answer... answers) {
        this.question = question;
        this.answers = Stream.of(answers).collect(Collectors.toSet());
        this.answers.forEach(x -> x.setQuestion(this));
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }
}

