package com.example.backend.account.vo;

import lombok.Data;

@Data
public class AccountVO {
    private String userId; // 계좌를 가진 사용자 ID
    private String accountNumber; // 계좌번호 14자리 (xxxxxx-xx-xxxxxx)
}
