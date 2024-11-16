package com.example.backend.account.service;

import com.example.backend.account.mapper.AccountMapper;
import com.example.backend.account.vo.AccountVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    // 1. 모임통장 개설 (새로운 모임 통장 계좌번호 추가)

    // 랜덤 계좌번호 생성
    private String generateRandomAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < 14; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }

    @Override
    public AccountVO createSharingAccount(String userId) {
        AccountVO sharingAccount = new AccountVO();
        sharingAccount.setUserId(userId);
        sharingAccount.setAccountNumber(generateRandomAccountNumber());
        accountMapper.insertSharingAccount(sharingAccount);
        return sharingAccount;
    }


    // 2.
}
