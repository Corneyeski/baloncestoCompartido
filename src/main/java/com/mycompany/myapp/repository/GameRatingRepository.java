package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Game;
import com.mycompany.myapp.domain.GameRating;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.GameDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the GameRating entity.
 */
@SuppressWarnings("unused")
public interface GameRatingRepository extends JpaRepository<GameRating,Long> {

    @Query("select gameRating from GameRating gameRating where gameRating.user.login = ?#{principal.username}")
    List<GameRating> findByUserIsCurrentUser();

    @Query("select avg(gameRating.score) from GameRating gameRating where gameRating.game.id = :gameId")
    Double findAverage(@Param("gameId") Long id);

    @Query("select gameRating.game, avg(gameRating.score) " +
        "from GameRating gameRating " +
        "group by gameRating.game "+
        "order by avg(gameRating.score) desc")
    List<Object[]> findTopFiveGames(Pageable pageable);

    @Query("select gameRating " +
        "from GameRating gameRating " +
        "where gameRating.game.id = :gameID " +
        "and gameRating.user.id = :userID")
    GameRating getRating(@Param("userID") Long userID, @Param("gameID") Long gameID);

    //Creamos la consulta para saber si un user ha votado a un game.
    Optional<GameRating> findByUserAndGame(User user, Game game);

    @Query("select gameRating.game,avg(gameRating.score) from GameRating gameRating group by gameRating.game")
    List<Object[]> findAllAverage();

}

