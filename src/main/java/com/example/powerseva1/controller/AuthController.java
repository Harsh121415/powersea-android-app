package com.example.powerseva1.controller;

import com.example.powerseva1.entity.User;
import com.example.powerseva1.repository.UserRepository;
import com.example.powerseva1.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    // REGISTER API
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {

        userRepository.save(user);

        return Map.of(
                "status", "success",
                "message", "User registered successfully",
                "email", user.getEmail()
        );
    }

    // LOGIN API
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {

        User dbUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = JwtUtil.generateToken(
                dbUser.getEmail(),
                dbUser.getRole()
        );

        return Map.of(
                "status", "success",
                "token", token,
                "role", dbUser.getRole(),
                "email", dbUser.getEmail()
        );
    }

    // UPLOAD PROFILE IMAGE API
    @PostMapping("/upload-profile/{userId}")
    public Map<String, Object> uploadProfileImage(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) throws Exception {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String uploadDir = "uploads/";

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path path = Paths.get(uploadDir + fileName);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        // user.setProfileImage(fileName);
        userRepository.save(user);

        return Map.of(
                "status", "success",
                "message", "Profile image uploaded successfully",
                "fileName", fileName,
                "userId", userId
        );
    }
}