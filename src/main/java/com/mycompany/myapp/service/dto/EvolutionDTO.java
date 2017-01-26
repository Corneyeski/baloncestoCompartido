package com.mycompany.myapp.service.dto;

import java.time.LocalDate;

/**
 * Created by User on 09/01/2017.
 */
public class EvolutionDTO {
    private LocalDate date;
    private Long numFavorites;

    public EvolutionDTO(){}

    public EvolutionDTO(LocalDate time, Long numFavorites) {
        this.date = time;
        this.numFavorites = numFavorites;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
            "date=" + date +
            ", numFavorites=" + numFavorites +
            '}';
    }
}
