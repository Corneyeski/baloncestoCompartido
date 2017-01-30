package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Game;
import com.mycompany.myapp.domain.GameRating;
import com.mycompany.myapp.repository.GameRatingRepository;
import com.mycompany.myapp.repository.GameRepository;
import com.mycompany.myapp.service.GameRatingService;
import com.mycompany.myapp.service.dto.GameDTO;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO Arnau control de errores

/**
 * REST controller for managing GameRating.
 */
@RestController
@RequestMapping("/api")
public class GameRatingResource {

    private final Logger log = LoggerFactory.getLogger(GameRatingResource.class);

    @Inject
    private GameRatingService gameRatingService;
    @Inject
    private GameRatingRepository gameRatingRepository;
    @Inject
    private GameRepository gameRepository;

    @PostMapping("/game-ratings")
    @Timed
    public ResponseEntity<GameRating> createGameRating(@RequestBody GameRating gameRating) throws URISyntaxException {
        log.debug("REST request to save GameRating : {}", gameRating);
        if (gameRating.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("gameRating", "idexists", "A new gameRating cannot already have an ID")).body(null);
        }

        ZonedDateTime now = ZonedDateTime.now();

        GameRating game = gameRatingRepository.getRating(gameRating.getUser().getId(), gameRating.getGame().getId());
        GameRating result;
        // si no hay ninguna puntuación a ese partido con ese usuario
        if(game == null){
            gameRating.setScoreDateTime(now);
            result = gameRatingService.save(gameRating);

        }else{
            //aqui el put
            GameRating aux = new GameRating(gameRating.getScore(), now, game.getUser(), game.getGame());
            gameRatingService.delete(game.getId());
            result = gameRatingService.save(aux);
        }

        return ResponseEntity.created(new URI("/api/game-ratings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("gameRating", result.getId().toString()))
            .body(result);
    }

    @PutMapping("/game-ratings")
    @Timed
    public ResponseEntity<GameRating> updateGameRating(@RequestBody GameRating gameRating) throws URISyntaxException {
        log.debug("REST request to update GameRating : {}", gameRating);
        if (gameRating.getId() == null) {
            return createGameRating(gameRating);
        }
        GameRating result = gameRatingService.save(gameRating);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("gameRating", gameRating.getId().toString()))
            .body(result);
    }

    @GetMapping("/game-ratings")
    @Timed
    public ResponseEntity<List<GameRating>> getAllGameRatings(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of GameRatings");
        Page<GameRating> page = gameRatingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/game-ratings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/game-ratings/{id}")
    @Timed
    public ResponseEntity<GameRating> getGameRating(@PathVariable Long id) {
        log.debug("REST request to get GameRating : {}", id);
        GameRating gameRating = gameRatingService.findOne(id);
        return Optional.ofNullable(gameRating)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/game-ratings/{id}")
    @Timed
    public ResponseEntity<Void> deleteGameRating(@PathVariable Long id) {
        log.debug("REST request to delete GameRating : {}", id);
        gameRatingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("gameRating", id.toString())).build();
    }

    //TODO Alan partido por id y la media de las valoraciones
    @GetMapping("/avgGame/{id}")
    @Timed
    public ResponseEntity<GameDTO> getGameRatingAvg(@PathVariable Long id) {
        log.debug("REST request to get GameRating : {}", id);
        Game game = gameRepository.findOne(id);
        Double media = gameRatingRepository.findAverage(id);

        GameDTO gameDTO = new GameDTO(game, media);

        return new ResponseEntity<>(gameDTO, HttpStatus.OK);
    }

    // TODO - Cristina
    @GetMapping("/top-five-games")
    @Timed
    public ResponseEntity<List<GameDTO>> getTopFiveGames() {
        log.debug("REST request to get TopFiveGames");

        List<Object[]> topFiveGames = gameRatingRepository.findTopFiveGames(new PageRequest(0, 5));

        List<GameDTO> result = topFiveGames.
            stream().
            map(game -> new GameDTO((Game) game[0], (Double) game[1])).
            collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
