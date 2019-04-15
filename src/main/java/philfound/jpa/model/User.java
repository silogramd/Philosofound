package philfound.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.data.domain.Page;


@Data
@EqualsAndHashCode

@Entity // This tells Hibernate to make a table out of this class
public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(unique = true)
    private String password;

    @NotNull
    @Column
    private String gender;

    @NotNull
    @Column
    private String incomeLevel;

    @NotNull
    @Column
    private String geography;

    @NotNull
    @Column
    private String party;

    @ManyToMany
    @JoinTable(
      name = "user_pick_answer",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "answer_id")
    )
    @JsonManagedReference
    List<Answer> votes;

    public User() {

    }

    public User(String u, String p, String g, String i, String geo, String party) {
        this.username = u;
        this.password = p;
        this.gender = g;
        this.incomeLevel = i;
        this.geography = geo;
        this.party = party;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

  public String getUsername() {
    return this.username;
  }


  public void setUsername(String u) {
    this.username = u;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String p) {
    this.password = p;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getIncomeLevel() {
    return this.incomeLevel;
  }

  public void setIncomeLevel(String i) {
    this.incomeLevel = i;
  }

  public String getGeography() {
    return this.geography;
  }

  public void setGeography(String geo) {
    this.geography = geo;
  }

  public String getParty() {
    return this.party;
  }

  public void setParty(String p) {
    this.party = p;
  }

  public ArrayList<Answer> getVotes() {
    ArrayList<Answer> temp = new ArrayList<Answer>();
    temp.addAll(this.votes);
    return temp;
  }
}
