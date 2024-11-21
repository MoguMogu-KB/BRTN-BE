package com.example.backend.roomdetails.controller;

import com.example.backend.roomdetails.dto.*;
import com.example.backend.exception.CustomNotFoundException;
import com.example.backend.roomdetails.service.RoomDetailsService;
import com.example.backend.roomlist.vo.RoomListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/roomdetails")
public class RoomDetailsController {

    private final RoomDetailsService roomDetailsService;

    @Autowired
    public RoomDetailsController(RoomDetailsService roomDetailsService) {
        this.roomDetailsService = roomDetailsService;
    }

    //12. 방 기본 정보 보여주기
    @GetMapping("/basic-info")
    public RoomDetailsDto getRoomBasicInfo(@RequestParam int roomNum) {
        return roomDetailsService.getRoomBasicInfo(roomNum);
    }

    // 13. 참여자 목록 출력
    @GetMapping("/participants")
    public List<ParticipantDto> getParticipantList(@RequestParam int roomNum) {
        return roomDetailsService.getParticipantList(roomNum);
    }

    // 14. 구독 계정 정보 출력
    @GetMapping("/subscription-account")
    public SubscriptionAccountDto getSubscriptionAccountInfo(@RequestParam int roomNum) {
        return roomDetailsService.getSubscriptionAccountInfo(roomNum);
    }

    // 15. 이번 달 납부 현황
    @GetMapping("/monthly-payment-status")
    public List<PaymentStatusDto> getMonthlyPaymentStatus(@RequestParam int roomNum) {
        return roomDetailsService.getMonthlyPaymentStatus(roomNum);
    }

    // 16. 모임통장 거래 내역 보여주기
    @GetMapping("/account-transactions")
    public List<TransactionDto> getAccountTransactionHistory(@RequestParam String accountNumber) {
        return roomDetailsService.getAccountTransactionHistory(accountNumber);
    }

    // 17. 이번 달 회비 납부하기 버튼 -> 모임통장 거래내역 추가
    @PostMapping("/add-transaction")
    public String insertTransaction(@RequestParam String accountNumber,
                                  @RequestParam String transactionDetails,
                                  @RequestParam int amount) {
        try {
            roomDetailsService.insertTransaction(accountNumber, transactionDetails, amount);
            return "Payment Successfully.";
        }catch (Exception e){
            return "Payment Failed.";
        }
    }
    //20 시작시 계정추가
    @PostMapping("/account")
    public ResponseEntity<String> addAccount(
            @RequestParam int roomNum,
            @RequestParam String subscribeId,
            @RequestParam(required = false, defaultValue = "") String subscribePwd
    ) {
        try {
            roomDetailsService.addAccount(roomNum,subscribeId, subscribePwd);
            return ResponseEntity.ok("계정이 성공적으로 추가되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("계정 추가 실패: " + e.getMessage());
        }
    }




    //21 계정 수정
    @PutMapping("/update-account/{roomNum}")
    public ResponseEntity<String> updateAccount(
            @PathVariable int roomNum,
            @RequestParam String subscribeId,
            @RequestParam String subscribePwd
    ) {
        try {
            roomDetailsService.updateAccount(roomNum, subscribeId, subscribePwd);

            return ResponseEntity.ok("success.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("fail");
        }
    }

    // 22. 비밀번호 랜덤 값 생성
    @GetMapping("/random/password")
    public ResponseEntity<String> randomPassword() {

        try {
            String password = roomDetailsService.randomPassword();
            return ResponseEntity.ok(password);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate random password.");
        }
    }

    // 27. 내가 참여 중인 방 목록 출력
    @GetMapping("/myrooms")
    public ResponseEntity<List<RoomListVO>> selectMyRoomList(@RequestParam String id) {
        try {
            List<RoomListVO> roomList = roomDetailsService.selectMyRoomList(id);
            return ResponseEntity.ok(roomList);
        } catch (CustomNotFoundException e) {
            log.info("404 Error : Roomlist not found by {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } catch (Exception e) {
            log.info("500 Error : An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}