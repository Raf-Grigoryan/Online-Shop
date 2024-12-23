package org.example.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.example.onlineshop.entity.user.UserType;
import org.example.onlineshop.service.CategoryService;
import org.example.onlineshop.service.security.CurrentUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;

    @Value("${online.shop.image.upload.path}")
    private String uploadPath;


    @GetMapping("/")
    public String getHomePage(ModelMap modelMap) {
        modelMap.put("categories", categoryService.findAll());
        return "home";
    }


    @GetMapping("/loginPage")
    public String loginPage(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            return "redirect:/";
        }
        return "/user/login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null && currentUser.getUser() != null) {
            if (currentUser.getUser().getUserType() == UserType.ADMIN) {
                return "redirect:/admin";
            }
        }
        return "redirect:/";
    }


    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("imageName") String imageName) throws IOException {
        File file = new File(uploadPath + imageName);
        if (file.exists()) {
            try (InputStream in = new FileInputStream(file)) {
                return IOUtils.toByteArray(in);
            }
        }
        return null;
    }
}
