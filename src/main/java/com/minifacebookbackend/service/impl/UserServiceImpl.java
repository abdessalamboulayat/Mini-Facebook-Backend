package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.UserCommand;
import com.minifacebookbackend.domain.model.User;
import com.minifacebookbackend.domain.representation.ImageRepresentation;
import com.minifacebookbackend.domain.representation.UserRepresentation;
import com.minifacebookbackend.mapper.UserMapper;
import com.minifacebookbackend.repository.UserRepository;
import com.minifacebookbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageServiceImpl imageService;
    @Override
    public User saveUser(UserCommand user) {
        if(user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user to save is null");
        }
        User userToSave = new User();
        userToSave.setEmail(user.getEmail());
        userToSave.setPassword(user.getPassword());
        userToSave.setLastName(user.getLastName());
        userToSave.setFirstName(user.getFirstName());
        userToSave.setBiography(user.getBiography());
        userToSave.setLocation(user.getLocation());
        userToSave.setCreatedAt(LocalDateTime.now().toString());
        return userRepository.save(userToSave);
    }
    @Override
    public UserRepresentation getById(String userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user id is not valid"));
        UserRepresentation userRepresentation = userMapper.toUserRepresentation(user);
        ImageRepresentation image=imageService.getImageByUserId(userId);
        if(image!=null){
            userRepresentation.setProfile(image);
        }
        return userRepresentation;
    }

    @Override
    public User updateUser(UserCommand userCommand, String userId) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user id is not valid"));
        userToUpdate.setPassword(userCommand.getPassword());
        userToUpdate.setLastName(userCommand.getLastName());
        userToUpdate.setFirstName(userCommand.getFirstName());
        userToUpdate.setBiography(userCommand.getBiography());
        userToUpdate.setLocation(userCommand.getLocation());
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user id is not valid"));
        userRepository.delete(user);
    }

    @Override
    public List<UserRepresentation> getAll(){
        List<User> users=userRepository.findAll().stream().toList();
        return userMapper.toUserRepresentationList(users);
    }

}
