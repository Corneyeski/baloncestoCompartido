package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.FavouritePlayer;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FavouritePlayer entity.
 */
@SuppressWarnings("unused")
public interface FavouritePlayerRepository extends JpaRepository<FavouritePlayer,Long> {

    @Query("select favouritePlayer from FavouritePlayer favouritePlayer where favouritePlayer.user.login = ?#{principal.username}")
    List<FavouritePlayer> findByUserIsCurrentUser();

}
