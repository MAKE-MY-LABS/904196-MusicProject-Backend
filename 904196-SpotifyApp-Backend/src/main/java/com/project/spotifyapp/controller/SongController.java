package com.project.spotifyapp.controller;

/*
 * Create a controller for the methods available in SongService
 * Autowire the SongService class
 * use throws keyword wherever required for exception handling
 */

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.spotifyapp.exception.SongAlreadyExistsException;
import com.project.spotifyapp.exception.SongNotFoundException;
import com.project.spotifyapp.model.Song;
import com.project.spotifyapp.service.SongService;

@RestController
@RequestMapping
@CrossOrigin
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/song")
    public Song saveSong(@RequestBody Song song) throws SongAlreadyExistsException {
        return songService.saveSong(song);
    }

    @DeleteMapping("/song/{entityId}")
    public String deleteSong(@PathVariable String entityId) throws SongNotFoundException {
        return songService.deleteSong(entityId);
    }

    @GetMapping("/saved-songs")
    public List<Song> getSavedSongs() {
        return songService.getSavedSongs();
    }

    @GetMapping(value = "/all-songs", produces = "application/json")
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

}