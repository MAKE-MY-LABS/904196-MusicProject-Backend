package com.project.spotifyapp.service;

/**
 * The class should implement the SongService interface.
 * If a song is not found, it should throw SongNotFoundException. 
 * If a song already exists, it should throw a SongAlreadyExistsException.
 * Use a RestTemplate bean to make calls to a third-party API.
 * Utilize RestTemplate to retrieve songs from the third-party API, GAANA_API_URL.
 * The value of GAANA_API_URL should be obtained from application.properties.
 */

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.spotifyapp.exception.SongAlreadyExistsException;
import com.project.spotifyapp.exception.SongNotFoundException;
import com.project.spotifyapp.model.Song;
import com.project.spotifyapp.model.SongList;
import com.project.spotifyapp.repository.SongRepository;


@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private RestTemplate restTemplate;

    // api url from properties file
    @Value("${GAANA_API_URL}")
    private String gaanaAPIUrl;

    // backup api url
    @Value("${BACKUP_API_URL}")
    private String backupAPIUrl;

    @Override
    public Song saveSong(Song song) throws SongAlreadyExistsException {
        if (songRepository.existsById(song.getEntity_id())) {
            throw new SongAlreadyExistsException("Song already exists");
        }
        return songRepository.save(song);
    }

    @Override
    public String deleteSong(String entityId) throws SongNotFoundException {
        if (!songRepository.existsById(entityId)) {
            throw new SongNotFoundException("Song not found");
        }
        songRepository.deleteById(entityId);
        return "Song deleted";
    }

    @Override
    public List<Song> getSavedSongs() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> getAllSongs() {
        return getGaanaSongs();
    }

    public List<Song> getGaanaSongs() {
        /**
         * construct the URL and utilize RestTemplate to retrieve stocks
         * from the third-party API, GAANA_API_URL.
         * Employ a RestTemplate bean to make calls to the third-party API.
         * Obtain the value of GAANA_API_URL from application.properties.
         */

        JSONObject response = null;

    
        ResponseEntity<String> backupResponse = restTemplate.exchange(
                backupAPIUrl,
                HttpMethod.GET, null, String.class);

        response = new JSONObject(backupResponse.getBody());
        
        ObjectMapper mapper = new ObjectMapper();
        List<Song> songs;
        try {
            songs = mapper.readValue(response.get("entities").toString(),
                    new TypeReference<List<Song>>() {
                    });
        } catch (JsonProcessingException | JSONException e) {
            songs = null;
            e.printStackTrace();
        }

        SongList songList = new SongList(songs);


        return songList.getSongs();

    }
}