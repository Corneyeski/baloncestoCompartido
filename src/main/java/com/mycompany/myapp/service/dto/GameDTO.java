package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Game;

/**
 * Created by User on 12/01/2017.
 */
public class GameDTO {

    Game game;
    Double avgScore;

    public GameDTO() {
    }

    public GameDTO(Game game, Double avgScore) {
        this.game = game;
        this.avgScore = avgScore;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    @Override
    public String toString() {
        return "GameDTO{" +
            "game=" + game +
            ", avgScore=" + avgScore +
            '}';
    }
}
