package com.example.backend.user.service;

import com.example.backend.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.user.mapper.UserMapper;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    // 1. 로그인 (아이디, 비밀번호 확인)
    @Override
    public UserVO findUserByIdPwd(String userId, String pwd) {
        return userMapper.findUserByIdPwd(userId, pwd);
    }

    // 2. 회원가입 (인적사항 추가)
    @Override
    public void insertUser(UserVO userVO) {
        userMapper.insertUser(userVO);
    }

    // 3-1. 프로필 변경 (이름, 성별, 나이 한 번에 변경)
    @Override
    public void updateUserInfo(UserVO userVO) {
        userMapper.updateUserInfo(userVO);
    }

    // 3-2. 비밀번호 변경
    @Override
    public void updateUserPwd(String userId, String pwd) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("pwd", pwd);

        userMapper.updateUserPwd(params);
    }

    // 4. 회원 탈퇴
    @Override
    public void deleteUser(String userId) {
        userMapper.deleteUser(userId);
    }
}
