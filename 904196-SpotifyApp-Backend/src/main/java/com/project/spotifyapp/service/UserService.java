package com.project.spotifyapp.service;

import java.util.Optional;

/* create the following methods
 * 1. validate user by checking the credentials  , if the emailId and password are correct return the user object
 * otherwise return null
 * 2. save user to the database  check if the user alraedy exists throw  UserAlreadyExistException
 * 
 * autowire the UserRepository
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spotifyapp.exception.UserAlreadyExistsException;
import com.project.spotifyapp.model.User;
import com.project.spotifyapp.repository.UserRepository;



@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User validateUser(String emailId, String password) {
        Optional<User>  op = userRepository.findByEmailIdAndPassword(emailId, password);
        if(op.isPresent()) {
            return op.get();
        }
        return null;
    }
    
    public User saveUser(User user) throws UserAlreadyExistsException {
    Optional<User>  op=userRepository.findById(user.getEmailId());
        if(op.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        return userRepository.save(user);
    }
}

 

 