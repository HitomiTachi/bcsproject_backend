package com.managerbcs.bcsproject_backend.service;

import com.managerbcs.bcsproject_backend.dao.UserRepository;
import com.managerbcs.bcsproject_backend.entity.MessageResponse;
import com.managerbcs.bcsproject_backend.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired private UserRepository userRepo;
    @Autowired private EmailService emailService;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<?> register(User user) {
        if (userRepo.existsByUsername(user.getUsername()))
            return ResponseEntity.badRequest().body(new MessageResponse("Username already exists"));
        if (userRepo.existsByEmail(user.getEmail()))
            return ResponseEntity.badRequest().body(new MessageResponse("Email already exists"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivated(false);
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        sendActivationEmail(user);
        return ResponseEntity.ok(new MessageResponse("Register success. Please activate via email."));
    }

    public ResponseEntity<?> activate(String email, String code) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body(new MessageResponse("User not found"));

        User user = userOpt.get();
        if (user.isActivated()) return ResponseEntity.badRequest().body(new MessageResponse("Account already activated"));
        if (!user.getActivationCode().equals(code)) return ResponseEntity.badRequest().body(new MessageResponse("Invalid activation code"));

        user.setActivated(true);
        userRepo.save(user);
        return ResponseEntity.ok(new MessageResponse("Account activated successfully"));
    }

    public ResponseEntity<?> login(String username, String password) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        if (userOpt.isEmpty() || !userOpt.get().isActivated())
            return ResponseEntity.status(401).body(new MessageResponse("Invalid or inactive account"));

        User user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword()))
            return ResponseEntity.status(401).body(new MessageResponse("Incorrect password"));

        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> forgotPassword(String input) {
        Optional<User> userOpt = userRepo.findByUsername(input);
        if (userOpt.isEmpty()) userOpt = userRepo.findByEmail(input);
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body(new MessageResponse("Account not found"));

        User user = userOpt.get();
        if (!user.isActivated()) return ResponseEntity.badRequest().body(new MessageResponse("Account not activated"));

        String newPass = randomPassword();
        user.setPassword(passwordEncoder.encode(newPass));
        userRepo.save(user);

        String subject = "Reset your password";
        String content = "<p>Your new password is: <b>" + newPass + "</b></p>";
        emailService.sendMessage("your_email@gmail.com", user.getEmail(), subject, content);
        return ResponseEntity.ok(new MessageResponse("New password sent to email"));
    }

    public ResponseEntity<?> changePassword(Integer id, String oldPass, String newPass) {
        Optional<User> userOpt = userRepo.findByUserId(id);
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body(new MessageResponse("User not found"));

        User user = userOpt.get();
        if (!passwordEncoder.matches(oldPass, user.getPassword()))
            return ResponseEntity.badRequest().body(new MessageResponse("Old password incorrect"));

        user.setPassword(passwordEncoder.encode(newPass));
        userRepo.save(user);
        return ResponseEntity.ok(new MessageResponse("Password changed successfully"));
    }

    private void sendActivationEmail(User user) {
        String url = "http://localhost:3000/activate/" + user.getEmail() + "/" + user.getActivationCode();
        String content = "<h2>Activate your account</h2><p>Click the link: <a href='" + url + "'>" + url + "</a></p>";
        emailService.sendMessage("your_email@gmail.com", user.getEmail(), "Activate Account", content);
    }

    private String randomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
