package com.example.backend.user.controller;

import com.example.backend.user.service.UserService;
import com.example.backend.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    // test
    @GetMapping("/test")
    public String testEndpoint() {
        return "Test endpoint is working";
    }

    // 1. 로그인 (아이디, 비밀번호 확인)
    @GetMapping("/login")
    public ResponseEntity<UserVO> login(@RequestParam("userId") String userId, @RequestParam("pwd") String pwd) {
        UserVO user = userService.findUserByIdPwd(userId, pwd);
        return ResponseEntity.ok(user);
    }

    // 2. 회원가입 (인적사항 추가)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserVO userVO) {
        userService.insertUser(userVO);
        return ResponseEntity.ok("User registered successfully");
    }

    // 3-1. 프로필 변경 (이름, 성별, 나이 한 번에 변경)
    @PutMapping("/{userId}/info")
    public ResponseEntity<String> updateUserInfo(@PathVariable String userId, @RequestBody UserVO userVO) {
        userVO.setUserId(userId); // PathVariable로 받은 userId를 UserVO에 설정
        userService.updateUserInfo(userVO);
        return ResponseEntity.ok("User info updated successfully");
    }

    // 3-2. 비밀번호 변경
    @PutMapping("/{userId}/password")
    public ResponseEntity<String> updateUserPwd(@PathVariable String userId, @RequestParam("pwd") String pwd) {
        userService.updateUserPwd(userId, pwd);
        return ResponseEntity.ok("Password updated successfully");
    }

    // 4. 회원 탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
