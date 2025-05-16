package com.managerbcs.bcsproject_backend.controller;

import com.managerbcs.bcsproject_backend.service.AuthService;
import com.managerbcs.bcsproject_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authAccountService;

    // ✅ Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) {
        return authAccountService.login(data.get("username"), data.get("password"));
    }

    // ✅ Quên mật khẩu (gửi mail mật khẩu mới)
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> data) {
        return authAccountService.forgotPassword(data.get("usernameOrEmail"));
    }

    // ✅ Đổi mật khẩu (yêu cầu userId và mật khẩu cũ)
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> data) {
        return authAccountService.changePassword(
                Integer.valueOf(data.get("userId")),
                data.get("oldPassword"),
                data.get("newPassword")
        );
    }
}
