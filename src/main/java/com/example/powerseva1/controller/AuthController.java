package com.example.powerseva1.controller;

import com.example.powerseva1.entity.User;
import com.example.powerseva1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.powerseva1.security.JwtUtil;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userRepository.save(user);
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        User dbUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = JwtUtil.generateToken(
                dbUser.getEmail(),
                dbUser.getRole()
        );

        return Map.of("token", token, "role", dbUser.getRole());
    }
    @PostMapping("/upload-profile/{userId}")
    public String uploadProfileImage(@PathVariable Long userId,
                                     @RequestParam("file") MultipartFile file) throws Exception {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String uploadDir = "uploads/";

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path path = Paths.get(uploadDir + fileName);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        user.setProfileImage(fileName);
        userRepository.save(user);

        return "Profile image uploaded successfully";
    }
}