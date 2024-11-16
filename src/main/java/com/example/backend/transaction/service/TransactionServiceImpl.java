package com.example.backend.transaction.service;

import com.example.backend.transaction.mapper.TransactionMapper;
import com.example.backend.transaction.vo.TransactionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper transactionMapper;

    // 1. 출금(송금, 이체) 내역 저장 - 거래 상대 계좌, 내역 입력
    public void insertSendingTransaction(TransactionVO transactionVO) {
        transactionMapper.insertSendingTransaction(transactionVO);
    }

    // 2. 해당 계좌의 전체 거래 내역 조회
    public List<TransactionVO> getTransactionHistory(String accountNumber){
        return transactionMapper.getTransactionHistory(accountNumber);
    }


    // 3.
}
