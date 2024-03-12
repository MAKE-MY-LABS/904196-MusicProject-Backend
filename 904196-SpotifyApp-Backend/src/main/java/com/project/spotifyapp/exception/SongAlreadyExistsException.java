package com.project.spotifyapp.exception;

/*
 * SongAlreadyExistsException EXTENDS Exception
 */

 public class SongAlreadyExistsException extends Exception {
    public SongAlreadyExistsException(String message) {
        super(message);
    }
}