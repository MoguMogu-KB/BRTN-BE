package com.example.backend.roomdetails.service;

import com.example.backend.roomdetails.dto.*;
import com.example.backend.roomdetails.vo.Transaction;
import com.example.backend.roomlist.vo.RoomListVO;
import com.example.backend.user.vo.UserVO;

import java.util.List;
import java.util.Date;

public interface RoomDetailsService {
    // 12. 방 기본 정보 출력
    RoomDetailsDto getRoomBasicInfo(int roomNum);

    // 13. 참여자 목록 출력
    List<ParticipantDto> getParticipantList(int roomNum);

    // 14. 구독 계정 정보 출력
    SubscriptionAccountDto getSubscriptionAccountInfo(int roomNum);

    // 15. 이번 달 납부 현황 출력
    List<PaymentStatusDto> getMonthlyPaymentStatus(int roomNum);

    // 16. 모임통장 거래 내역 출력
    List<TransactionDto> getAccountTransactionHistory(String accountNumber);

    // 17. 이번 달 회비 납부하기
    void insertTransaction(String accountNumber, String transactionDetails, int amount);

    // 22. 비밀번호 랜덤 값 생성
    String randomPassword();

    // 27. 내가 참여 중인 방 목록 출력
    List<RoomListVO> selectMyRoomList(String id);

    //20 시작시 아이디 비밀번호 추가
    void addAccount(int roomNun, String subscribeId, String subscribePwd);

    //21 계정 수정
    void updateAccount(int roomNum, String subscribeId, String subscribePwd);

}
