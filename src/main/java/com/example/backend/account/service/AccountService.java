package com.example.backend.account.service;

import com.example.backend.account.vo.AccountVO;

public interface AccountService {
    // 1. 모임통장 개설 (새로운 모임 통장 계좌번호 추가)
    public AccountVO createSharingAccount(String userId);

    // 2.
}
