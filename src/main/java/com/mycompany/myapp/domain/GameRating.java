package com.mycompany.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A GameRating.
 */
@Entity
@Table(name = "game_rating")
public class GameRating implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "score")
    private Integer score;

    @Column(name = "score_date_time")
    private ZonedDateTime scoreDateTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Game game;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public GameRating score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public ZonedDateTime getScoreDateTime() {
        return scoreDateTime;
    }

    public GameRating scoreDateTime(ZonedDateTime scoreDateTime) {
        this.scoreDateTime = scoreDateTime;
        return this;
    }

    public void setScoreDateTime(ZonedDateTime scoreDateTime) {
        this.scoreDateTime = scoreDateTime;
    }

    public User getUser() {
        return user;
    }

    public GameRating user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public GameRating game(Game game) {
        this.game = game;
        return this;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameRating gameRating = (GameRating) o;
        if(gameRating.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, gameRating.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "GameRating{" +
            "id=" + id +
            ", score='" + score + "'" +
            ", scoreDateTime='" + scoreDateTime + "'" +
            '}';
    }
}
