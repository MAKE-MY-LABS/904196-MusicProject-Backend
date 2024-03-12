package com.project.spotifyapp.service;

/*
 * Create methods in the StocksService interface to perform the following
 * operations:
 * 
 * Save a Song.
 * Delete a Song based on its entity_id.
 * Retrieve all Songs.
 * Retrieve all Saved Songs.
 * Search Songs by name
 * 
 */

import java.util.List;

import com.project.spotifyapp.exception.SongAlreadyExistsException;
import com.project.spotifyapp.exception.SongNotFoundException;
import com.project.spotifyapp.model.Song;



public interface SongService {
    public Song saveSong(Song song) throws SongAlreadyExistsException;

    public String deleteSong(String entityId) throws SongNotFoundException;

    public List<Song> getAllSongs();

    public List<Song> getSavedSongs();

}