package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.FavouritePlayer;
import com.mycompany.myapp.repository.FavouritePlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing FavouritePlayer.
 */
@Service
@Transactional
public class FavouritePlayerService {

    private final Logger log = LoggerFactory.getLogger(FavouritePlayerService.class);
    
    @Inject
    private FavouritePlayerRepository favouritePlayerRepository;

    /**
     * Save a favouritePlayer.
     *
     * @param favouritePlayer the entity to save
     * @return the persisted entity
     */
    public FavouritePlayer save(FavouritePlayer favouritePlayer) {
        log.debug("Request to save FavouritePlayer : {}", favouritePlayer);
        FavouritePlayer result = favouritePlayerRepository.save(favouritePlayer);
        return result;
    }

    /**
     *  Get all the favouritePlayers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<FavouritePlayer> findAll(Pageable pageable) {
        log.debug("Request to get all FavouritePlayers");
        Page<FavouritePlayer> result = favouritePlayerRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one favouritePlayer by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public FavouritePlayer findOne(Long id) {
        log.debug("Request to get FavouritePlayer : {}", id);
        FavouritePlayer favouritePlayer = favouritePlayerRepository.findOne(id);
        return favouritePlayer;
    }

    /**
     *  Delete the  favouritePlayer by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FavouritePlayer : {}", id);
        favouritePlayerRepository.delete(id);
    }
}
