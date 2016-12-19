package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.mycompany.myapp.domain.enumeration.Position;

/**
 * A Player.
 */
@Entity
@Table(name = "player")
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "num_baskets")
    private Integer numBaskets;

    @Column(name = "num_assists")
    private Integer numAssists;

    @Column(name = "num_rebounds")
    private Integer numRebounds;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "player")
    @JsonIgnore
    private Set<FavouritePlayer> favouritePlayers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Player name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public Player surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Player birthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getNumBaskets() {
        return numBaskets;
    }

    public Player numBaskets(Integer numBaskets) {
        this.numBaskets = numBaskets;
        return this;
    }

    public void setNumBaskets(Integer numBaskets) {
        this.numBaskets = numBaskets;
    }

    public Integer getNumAssists() {
        return numAssists;
    }

    public Player numAssists(Integer numAssists) {
        this.numAssists = numAssists;
        return this;
    }

    public void setNumAssists(Integer numAssists) {
        this.numAssists = numAssists;
    }

    public Integer getNumRebounds() {
        return numRebounds;
    }

    public Player numRebounds(Integer numRebounds) {
        this.numRebounds = numRebounds;
        return this;
    }

    public void setNumRebounds(Integer numRebounds) {
        this.numRebounds = numRebounds;
    }

    public Position getPosition() {
        return position;
    }

    public Player position(Position position) {
        this.position = position;
        return this;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public Player team(Team team) {
        this.team = team;
        return this;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<FavouritePlayer> getFavouritePlayers() {
        return favouritePlayers;
    }

    public Player favouritePlayers(Set<FavouritePlayer> favouritePlayers) {
        this.favouritePlayers = favouritePlayers;
        return this;
    }

    public Player addFavouritePlayer(FavouritePlayer favouritePlayer) {
        favouritePlayers.add(favouritePlayer);
        favouritePlayer.setPlayer(this);
        return this;
    }

    public Player removeFavouritePlayer(FavouritePlayer favouritePlayer) {
        favouritePlayers.remove(favouritePlayer);
        favouritePlayer.setPlayer(null);
        return this;
    }

    public void setFavouritePlayers(Set<FavouritePlayer> favouritePlayers) {
        this.favouritePlayers = favouritePlayers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        if(player.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Player{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", surname='" + surname + "'" +
            ", birthdate='" + birthdate + "'" +
            ", numBaskets='" + numBaskets + "'" +
            ", numAssists='" + numAssists + "'" +
            ", numRebounds='" + numRebounds + "'" +
            ", position='" + position + "'" +
            '}';
    }
}
