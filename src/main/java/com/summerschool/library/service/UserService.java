package com.summerschool.library.service;

import com.summerschool.library.exception.EmailAlreadyUsedException;
import com.summerschool.library.model.domain.User;
import com.summerschool.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User user) {
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        return userRepository.save(user);
    }

}
