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
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    //@NotNull
    //@Column(unique = true)
    private String question;
    
    //@OneToMany(mappedBy="question", cascade = CascadeType.ALL)
    //private Set<Answer> answers;

    public Question() {

    }

    public Question(String question) {
        this.question = question;
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
}
