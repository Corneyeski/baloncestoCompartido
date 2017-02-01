package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Player;

import java.util.List;

/**
 * Created by Alan on 20/01/2017.
 */
public class EvolutionGeneralJugDTO {

    private Player player;
    private List<EvolutionDTO> evolutionGeneral;

    public EvolutionGeneralJugDTO() {}

    public EvolutionGeneralJugDTO(Player player, List<EvolutionDTO> evolutionGeneral) {
        this.player = player;
        this.evolutionGeneral = evolutionGeneral;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<EvolutionDTO> getEvolutionGeneral() {
        return evolutionGeneral;
    }

    public void setEvolutionGeneral(List<EvolutionDTO> evolutionGeneral) {
        this.evolutionGeneral = evolutionGeneral;
    }

    @Override
    public String toString() {
        return "EvolutionGeneralJugDTO{" +
            "player=" + player +
            ", evolutionGeneral=" + evolutionGeneral +
            '}';
    }
}
