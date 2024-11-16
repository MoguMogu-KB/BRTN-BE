package com.example.backend.transaction.mapper;

import com.example.backend.transaction.vo.TransactionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {
    // 1. 출금(송금, 이체) 내역 저장 - 거래 상대 계좌, 내역 입력
    public void insertSendingTransaction(TransactionVO transactionVO);

    // 2. 해당 계좌의 전체 거래 내역 조회
    List<TransactionVO> getTransactionHistory(String accountNumber);

    // 3.
}
