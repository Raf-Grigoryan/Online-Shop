package org.example.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.example.onlineshop.entity.user.User;
import org.example.onlineshop.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(@AuthenticationPrincipal User currentUser) {
        if (currentUser != null) {
            return "redirect:/";
        }
        return "/user/register";
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute User user, @RequestParam("image") MultipartFile image) {
        userService.register(user, image);
        return "redirect:/";
    }
}
