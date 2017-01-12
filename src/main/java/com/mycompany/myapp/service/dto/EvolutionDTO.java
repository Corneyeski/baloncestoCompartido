package com.mycompany.myapp.service.dto;

import java.time.LocalDate;

/**
 * Created by User on 09/01/2017.
 */
public class EvolutionDTO {
    private LocalDate time;
    private Long numFavorites;

    public EvolutionDTO(){}

    public EvolutionDTO(LocalDate time, Long numFavorites) {
        this.time = time;
        this.numFavorites = numFavorites;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Long getNumFavorites() {
        return numFavorites;
    }

    public void setNumFavorites(Long numFavorites) {
        this.numFavorites = numFavorites;
    }

   @Override
    public String toString() {
        return "EvolutionDTO{" +
            "time=" + time +
            ", numFavorites=" + numFavorites +
            '}';
    }
}
