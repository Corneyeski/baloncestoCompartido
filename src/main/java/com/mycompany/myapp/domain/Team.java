package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Team.
 */
@Entity
@Table(name = "team")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "foundation_date")
    private LocalDate foundationDate;

    @OneToMany(mappedBy = "localTeam")
    @JsonIgnore
    private Set<Game> localTeamGames = new HashSet<>();

    @OneToMany(mappedBy = "visitorTeam")
    @JsonIgnore
    private Set<Game> visitorTeamGames = new HashSet<>();

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private Set<Player> players = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Team name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public Team city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public Team foundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
        return this;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    public Set<Game> getLocalTeamGames() {
        return localTeamGames;
    }

    public Team localTeamGames(Set<Game> games) {
        this.localTeamGames = games;
        return this;
    }

    public Team addLocalTeamGame(Game game) {
        localTeamGames.add(game);
        game.setLocalTeam(this);
        return this;
    }

    public Team removeLocalTeamGame(Game game) {
        localTeamGames.remove(game);
        game.setLocalTeam(null);
        return this;
    }

    public void setLocalTeamGames(Set<Game> games) {
        this.localTeamGames = games;
    }

    public Set<Game> getVisitorTeamGames() {
        return visitorTeamGames;
    }

    public Team visitorTeamGames(Set<Game> games) {
        this.visitorTeamGames = games;
        return this;
    }

    public Team addVisitorTeamGame(Game game) {
        visitorTeamGames.add(game);
        game.setVisitorTeam(this);
        return this;
    }

    public Team removeVisitorTeamGame(Game game) {
        visitorTeamGames.remove(game);
        game.setVisitorTeam(null);
        return this;
    }

    public void setVisitorTeamGames(Set<Game> games) {
        this.visitorTeamGames = games;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Team players(Set<Player> players) {
        this.players = players;
        return this;
    }

    public Team addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
        return this;
    }

    public Team removePlayer(Player player) {
        players.remove(player);
        player.setTeam(null);
        return this;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        if(team.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Team{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", city='" + city + "'" +
            ", foundationDate='" + foundationDate + "'" +
            '}';
    }
}
