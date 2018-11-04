package com.userRegistrationService.userregistration.service;

import com.userRegistrationService.userregistration.Exception.UserAlreadyExistsException;
import com.userRegistrationService.userregistration.domain.User;
import com.userRegistrationService.userregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.existsById(user.getEmail())){
            throw new UserAlreadyExistsException("Movie already Exists");
        }
        User saveduser= userRepository.save(user);
        if(saveduser==null)
            throw new UserAlreadyExistsException("Movie already Exists");
        return saveduser;
    }

    @Override
    public User updateUserById(String id, User user) {
        User newuser=null;
        if(userRepository.existsById(id)) {
            newuser = getUserById(id);
            newuser.setCity(user.getCity());
            newuser.setEmail(user.getEmail());
            newuser.setGender(user.getGender());
            newuser.setName(user.getName());
            newuser.setPhoneNumber(user.getPhoneNumber());
        }
        User savedmovie=userRepository.save(user);
        return savedmovie;
    }

    @Override
    public User getUserById(String id) {
        User user= userRepository.findById(id).get();
        return user;
    }
}
