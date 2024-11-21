package com.example.backend.user.controller;

import com.example.backend.exception.CustomNotFoundException;
import com.example.backend.user.service.UserService;
import com.example.backend.user.vo.SurveyVO;
import com.example.backend.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    // 1. ID와 비밀번호 일치 여부 확인
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam("userId") String userId, @RequestParam("pwd") String pwd) {
        String loginId = userService.findUserByIdPwd(userId, pwd);
        return ResponseEntity.ok(loginId);
    }

    // 2. 아이디 중복 확인
    @GetMapping("/checkDuplicatedId")
    public ResponseEntity<Boolean> checkDuplicateId(@RequestParam String userId) {
        boolean isDuplicate = userService.isUserIdDuplicate(userId);
        return ResponseEntity.ok(isDuplicate);
    }

    // 3. 사용자 정보 저장
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserVO userVO) {
        userService.insertUser(userVO);
        return ResponseEntity.ok("User registered successfully");
    }

    // 설문조사
    // 4-1. 설문조사 데이터 저장
    @PostMapping("/survey/submit")
    public ResponseEntity<String> insertSurvey(@RequestBody SurveyVO surveyVO) {
        try {
            log.info("rsult = {}", surveyVO.getUserId());
            userService.insertSurvey(surveyVO);
            return ResponseEntity.status(HttpStatus.OK).body("Survey saved successfully.");
        } catch (CustomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to save survey.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save survey.");
        }
    }

    // 4-2. 설문조사 데이터 조회
    @GetMapping("/survey/{userId}")
    public ResponseEntity<SurveyVO> getSurvey(@PathVariable String userId) {
        SurveyVO surveyData = userService.getSurvey(userId);
        return ResponseEntity.ok(surveyData);
    }

    // 4. 회원 탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    // 24. 개인정보 출력
    @GetMapping("{userId}/info")
    public ResponseEntity<UserVO> login(@PathVariable("userId") String userId) {
        UserVO user = userService.selectInfo(userId);
        return ResponseEntity.ok(user);
    }


    //28.경고 상태 보여주기
    @GetMapping("/warning/{userId}")
    public ResponseEntity<UserVO> warning(@PathVariable String userId) {
        UserVO user = userService.showWarning(userId);
        return ResponseEntity.ok(user);
    }
    //29.경고 계정 보여주기
    @PostMapping("/add-warning")
    public ResponseEntity<String> addWarning(@RequestParam String name) {
        try {
            userService.addWarningByName(name);
            return ResponseEntity.ok("Warning count increased for user: " + name);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add warning: " + e.getMessage());
        }

    // 25. 개인정보 수정
    @PutMapping("/{userId}/update/info")
    public ResponseEntity<String> updateUserInfo(@PathVariable String userId, @RequestBody UserVO userVO) {
        userVO.setUserId(userId); // PathVariable로 받은 userId를 UserVO에 설정
        userService.updateUserInfo(userVO);
        return ResponseEntity.ok("User info updated successfully");
    }

    // 26. 비밀번호 변경
    @PutMapping("/{userId}/update/password")
    public ResponseEntity<String> updateUserPwd(@PathVariable String userId, @RequestParam("pwd") String pwd) {
        userService.updateUserPwd(userId, pwd);
        return ResponseEntity.ok("Password updated successfully");
    }
}
