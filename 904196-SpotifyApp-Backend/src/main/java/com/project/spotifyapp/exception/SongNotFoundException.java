package com.project.spotifyapp.exception;

/**
 * SongNotFoundException EXTENDS Exception
 */

public class SongNotFoundException extends Exception {
    public SongNotFoundException(String message) {
        super(message);
    }
}