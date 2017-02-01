package com.mycompany.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A FavouritePlayer.
 */
@Entity
@Table(name = "favourite_player")
public class FavouritePlayer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "favourite_date_time")
    private LocalDateTime favouriteDateTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Player player;

    public FavouritePlayer(LocalDateTime favouriteDateTime, User user, Player player) {
        this.favouriteDateTime = favouriteDateTime;
        this.user = user;
        this.player = player;
    }

    public FavouritePlayer(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFavouriteDateTime() {
        return favouriteDateTime;
    }

    public FavouritePlayer favouriteDateTime(LocalDateTime favouriteDateTime) {
        this.favouriteDateTime = favouriteDateTime;
        return this;
    }

    public void setFavouriteDateTime(LocalDateTime favouriteDateTime) {
        this.favouriteDateTime = favouriteDateTime;
    }

    public User getUser() {
        return user;
    }

    public FavouritePlayer user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Player getPlayer() {
        return player;
    }

    public FavouritePlayer player(Player player) {
        this.player = player;
        return this;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FavouritePlayer favouritePlayer = (FavouritePlayer) o;
        if(favouritePlayer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, favouritePlayer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FavouritePlayer{" +
            "id=" + id +
            ", favouriteDateTime='" + favouriteDateTime + "'" +
            '}';
    }
}
