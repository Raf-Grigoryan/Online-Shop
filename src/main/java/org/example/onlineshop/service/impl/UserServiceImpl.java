package org.example.onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.onlineshop.entity.user.User;
import org.example.onlineshop.entity.user.UserType;
import org.example.onlineshop.repository.UserRepository;
import org.example.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Value("${online.shop.image.upload.path}")
    private String uploadPath;

    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public void register(User user, MultipartFile multipartFile) {
        String fileName;
        if (!multipartFile.isEmpty()) {
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            user.setPictureName(fileName);
        } else {
            user.setPictureName("default.jpg");
        }
        user.setUserType(UserType.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
