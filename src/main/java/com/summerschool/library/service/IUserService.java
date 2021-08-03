package com.summerschool.library.service;

import com.summerschool.library.model.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {

    Optional<User> get(Long id);

    List<User> getAll();

    User add(User user);
}
