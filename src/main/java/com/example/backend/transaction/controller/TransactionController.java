package com.example.backend.transaction.controller;

import com.example.backend.transaction.service.TransactionService;
import com.example.backend.transaction.vo.TransactionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TransactionController {

    private final TransactionService transactionService;

    // 1. 출금(송금, 이체) 내역 저장 - 거래 상대 계좌, 내역 입력
    @PostMapping("/send")
    public ResponseEntity<String> sendTransaction(@RequestBody TransactionVO transactionVO) {
        try {
            // 넣어야 하는 필수 정보
            if (transactionVO.getAmount() <= 0) {
                return ResponseEntity.badRequest().body("Transaction amount must be greater than zero.");
            }
            if (transactionVO.getAccountNumber() == null || transactionVO.getAccountNumber().isEmpty()) {
                return ResponseEntity.badRequest().body("Account number is required.");
            }
            if (transactionVO.getInformation() == null || transactionVO.getInformation().isEmpty()) {
                return ResponseEntity.badRequest().body("Recipient information is required.");
            }

            // 송금이므로 amount 마이너스 처리
            transactionVO.setAmount(-Math.abs(transactionVO.getAmount()));

            // 거래 시간 자동 찍힘
            transactionVO.setTime(new Timestamp(System.currentTimeMillis()));

            transactionService.insertSendingTransaction(transactionVO);
            return ResponseEntity.ok("Transaction sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the transaction: " + e.getMessage());
        }
    }


    // 2. 해당 계좌의 전체 거래 내역 모두 조회
    @GetMapping("/history")
    public ResponseEntity<List<TransactionVO>> getTransactionHistory(@RequestParam String accountNumber) {
        try {
            if (accountNumber == null || accountNumber.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            List<TransactionVO> transactionHistory = transactionService.getTransactionHistory(accountNumber);
            return ResponseEntity.ok(transactionHistory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList()); // 에러 발생 시 빈 목록 반환
        }
    }

    // 3.
}
