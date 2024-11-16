package com.example.backend.transaction.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TransactionVO {
    private Timestamp time;         // 거래 시간
    private String accountNumber;   // 거래 계좌 번호
    private String information;     // 거래 정보 (거래 상대방 실명)
    private int amount;             // 거래 금액 (출금, 입금은 +-로 표시)
}
