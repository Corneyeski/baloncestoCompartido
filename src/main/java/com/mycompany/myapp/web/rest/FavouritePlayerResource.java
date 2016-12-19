package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.FavouritePlayer;
import com.mycompany.myapp.service.FavouritePlayerService;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FavouritePlayer.
 */
@RestController
@RequestMapping("/api")
public class FavouritePlayerResource {

    private final Logger log = LoggerFactory.getLogger(FavouritePlayerResource.class);
        
    @Inject
    private FavouritePlayerService favouritePlayerService;

    /**
     * POST  /favourite-players : Create a new favouritePlayer.
     *
     * @param favouritePlayer the favouritePlayer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new favouritePlayer, or with status 400 (Bad Request) if the favouritePlayer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/favourite-players")
    @Timed
    public ResponseEntity<FavouritePlayer> createFavouritePlayer(@RequestBody FavouritePlayer favouritePlayer) throws URISyntaxException {
        log.debug("REST request to save FavouritePlayer : {}", favouritePlayer);
        if (favouritePlayer.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("favouritePlayer", "idexists", "A new favouritePlayer cannot already have an ID")).body(null);
        }
        FavouritePlayer result = favouritePlayerService.save(favouritePlayer);
        return ResponseEntity.created(new URI("/api/favourite-players/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("favouritePlayer", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /favourite-players : Updates an existing favouritePlayer.
     *
     * @param favouritePlayer the favouritePlayer to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated favouritePlayer,
     * or with status 400 (Bad Request) if the favouritePlayer is not valid,
     * or with status 500 (Internal Server Error) if the favouritePlayer couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/favourite-players")
    @Timed
    public ResponseEntity<FavouritePlayer> updateFavouritePlayer(@RequestBody FavouritePlayer favouritePlayer) throws URISyntaxException {
        log.debug("REST request to update FavouritePlayer : {}", favouritePlayer);
        if (favouritePlayer.getId() == null) {
            return createFavouritePlayer(favouritePlayer);
        }
        FavouritePlayer result = favouritePlayerService.save(favouritePlayer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("favouritePlayer", favouritePlayer.getId().toString()))
            .body(result);
    }

    /**
     * GET  /favourite-players : get all the favouritePlayers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of favouritePlayers in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/favourite-players")
    @Timed
    public ResponseEntity<List<FavouritePlayer>> getAllFavouritePlayers(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of FavouritePlayers");
        Page<FavouritePlayer> page = favouritePlayerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/favourite-players");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /favourite-players/:id : get the "id" favouritePlayer.
     *
     * @param id the id of the favouritePlayer to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the favouritePlayer, or with status 404 (Not Found)
     */
    @GetMapping("/favourite-players/{id}")
    @Timed
    public ResponseEntity<FavouritePlayer> getFavouritePlayer(@PathVariable Long id) {
        log.debug("REST request to get FavouritePlayer : {}", id);
        FavouritePlayer favouritePlayer = favouritePlayerService.findOne(id);
        return Optional.ofNullable(favouritePlayer)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /favourite-players/:id : delete the "id" favouritePlayer.
     *
     * @param id the id of the favouritePlayer to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/favourite-players/{id}")
    @Timed
    public ResponseEntity<Void> deleteFavouritePlayer(@PathVariable Long id) {
        log.debug("REST request to delete FavouritePlayer : {}", id);
        favouritePlayerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("favouritePlayer", id.toString())).build();
    }

}
