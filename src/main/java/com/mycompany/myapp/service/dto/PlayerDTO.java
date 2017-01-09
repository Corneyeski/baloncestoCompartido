package com.mycompany.myapp.service.dto;
import com.mycompany.myapp.domain.Player;

public class PlayerDTO {
    private Player player;
    private Long numFavs;

    public PlayerDTO() {
    }

    public PlayerDTO(Player player, Long numFavs) {
        this.player = player;
        this.numFavs = numFavs;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Long getNumFavs() {
        return numFavs;
    }

    public void setNumFavs(Long numFavs) {
        this.numFavs = numFavs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerDTO playerDTO = (PlayerDTO) o;

        if (player != null ? !player.equals(playerDTO.player) : playerDTO.player != null) return false;
        return numFavs != null ? numFavs.equals(playerDTO.numFavs) : playerDTO.numFavs == null;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (numFavs != null ? numFavs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
            "player=" + player +
            ", numFavs=" + numFavs +
            '}';
    }
}
