package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.GameRating;
import com.mycompany.myapp.repository.GameRatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Service Implementation for managing GameRating.
 */
@Service
@Transactional
public class GameRatingService {

    private final Logger log = LoggerFactory.getLogger(GameRatingService.class);

    @Inject
    private GameRatingRepository gameRatingRepository;

    /**
     * Save a gameRating.
     *
     * @param gameRating the entity to save
     * @return the persisted entity
     */
    public GameRating save(GameRating gameRating) {
        log.debug("Request to save GameRating : {}", gameRating);
        GameRating result = gameRatingRepository.save(gameRating);
        return result;
    }

    /**
     *  Get all the gameRatings.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<GameRating> findAll(Pageable pageable) {
        log.debug("Request to get all GameRatings");
        Page<GameRating> result = gameRatingRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one gameRating by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public GameRating findOne(Long id) {
        log.debug("Request to get GameRating : {}", id);
        GameRating gameRating = gameRatingRepository.findOne(id);
        return gameRating;
    }

    /**
     *  Delete the  gameRating by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete GameRating : {}", id);
        gameRatingRepository.delete(id);
    }
}
