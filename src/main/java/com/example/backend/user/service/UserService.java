package com.example.backend.user.service;

import com.example.backend.user.vo.UserVO;

import java.util.Map;

public interface UserService {

    // 1. 로그인 (아이디, 비밀번호 확인)
    UserVO findUserByIdPwd(String userId, String pwd);

    // 2. 회원가입 (인적사항 추가)
    void insertUser(UserVO userVO);

    // 3-1. 프로필 변경 (이름, 성별, 나이 한 번에 변경)
    void updateUserInfo(UserVO userVO);

    // 3-2. 비밀번호 변경
    public void updateUserPwd(String userId, String pwd);

    // 4. 회원 탈퇴
    void deleteUser(String userId);
}
