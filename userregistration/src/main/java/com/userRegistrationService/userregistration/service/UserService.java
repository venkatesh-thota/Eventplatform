package com.userRegistrationService.userregistration.service;

import com.userRegistrationService.userregistration.Exception.UserAlreadyExistsException;
import com.userRegistrationService.userregistration.domain.User;

public interface UserService {
    User saveUser (User user) throws UserAlreadyExistsException;
    User updateUserById(String id,User user);
    User getUserById(String id);
}
