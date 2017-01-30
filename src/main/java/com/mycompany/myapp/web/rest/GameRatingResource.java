package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.GameRating;
import com.mycompany.myapp.repository.GameRatingRepository;
import com.mycompany.myapp.repository.GameRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.GameRatingService;
import com.mycompany.myapp.service.GameService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

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
    private GameRepository gameRepository;

    @Inject
    private GameRatingRepository gameRatingRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private GameService gameService;

    @PostMapping("/game-ratings")
    @Timed
    public ResponseEntity<GameRating> createGameRating(@RequestBody GameRating gameRating) throws URISyntaxException {
        log.debug("REST request to save GameRating : {}", gameRating);
        if (gameRating.getId() != null) {
            //Comprobamos si el id de la valoracion existe, si existe regresamos un Failure Alert
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("gameRating", "idexists", "A new gameRating cannot already have an ID")).body(null);
        }

        if(gameRepository.findOne(gameRating.getGame().getId())==null){
            //Comprobamos si el objeto gameRating existe, si no existe regresamos un 403 bad request
            return ResponseEntity.badRequest().
                headers(HeaderUtil.createFailureAlert("gameRating","gameNotExistant","Game doesn't exists")).body(null);
        }

        gameRating.setUser(userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get());
        //Colocamos dentro del gameRating que pasamos EL USUARIO QUE ESTA LOGGEADO
        gameRating.setScoreDateTime(ZonedDateTime.now());
        //Le colocamos el tiempo de hoy, ya que bien vaya a actualizar o crear, será hoy cuando lo haga

        Optional<GameRating> gameRatingOptional = gameRatingRepository.findByUserAndGame(gameRating.getUser(),gameRating.getGame());

        //buscamos una valoracion de ese usuario y del juego que pasamos por parametro
        //Lo envolvemos en un Optional porque puede regresar un valor o un null
        //OJO dentro del repository la consulta tambien debe tener un Optional

        GameRating result = null;

        if(gameRatingOptional.isPresent()){
            //Si gameRatingOptional tiene valor, existe la valoracion, entonces actualizamos los datos
            result = gameRatingOptional.get();
            result.setScore(gameRating.getScore());
            //le colocamos como score al objeto result el score del gameRating que pasamos por parametro
            result.setScoreDateTime(gameRating.getScoreDateTime());
            //Podriamos colocar result.setScoreDateTime(ZonedDateTime.now()), pero tendria mas coste, asi que aprovechamos que ya
            //le colocamos a gameRating un ZonedDateTime.now()
            return updateGameRating(result);
            //De aqui lo enviamos al PUT, que se encargará de actualizar los datos
        }else{
            //si no existe una valoracion, es decir, gameRatingOptional es null, llamamos al repository y guardamos el gameRating
            //Finalmente regresamos la URL con la ruta del gameRating creado
            result = gameRatingRepository.save(gameRating);
            return ResponseEntity.created(new URI("/api/game-ratings/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("gameRating", result.getId().toString()))
                .body(result);
        }
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
    //TODO Alfredo top-five partidos
}
