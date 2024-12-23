package org.example.onlineshop.service;

import org.example.onlineshop.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {

    void register(User user, MultipartFile file);

    Optional<User> findByEmail(String username);
}
