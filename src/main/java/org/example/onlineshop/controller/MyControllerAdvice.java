package org.example.onlineshop.controller;

import org.example.onlineshop.entity.user.User;
import org.example.onlineshop.service.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvice {

    @ModelAttribute("currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            System.out.println("currentUser = " + currentUser);
            return currentUser.getUser();
        }
        return null;
    }

}
