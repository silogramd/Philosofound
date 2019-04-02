package philfound.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.*;
import javax.persistence.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@Table(name = "answers")
@Entity // This tells Hibernate to make a table out of this class
public class Answer extends AuditModel{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Lob
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public Answer() {

    }

    public Answer(String answer) {
        this.answer = answer;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

  public Long getUserId() {
    return user.getId();
  }

	public String getText() {
		return answer;
	}

	public void setText(String answer) {
		this.answer = answer;
	}

    public void setQuestion(Question question) {
        this.question = question;
    }
}
