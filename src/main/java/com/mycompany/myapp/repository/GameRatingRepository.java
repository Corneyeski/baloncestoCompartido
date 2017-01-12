package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.GameRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the GameRating entity.
 */
@SuppressWarnings("unused")
public interface GameRatingRepository extends JpaRepository<GameRating,Long> {

    @Query("select gameRating from GameRating gameRating where gameRating.user.login = ?#{principal.username}")
    List<GameRating> findByUserIsCurrentUser();

    @Query("select avg(gameRating.score) from GameRating gameRating where gameRating.game.id = :gameId")
    Double findAverage(@Param("gameId") Long id);

}
