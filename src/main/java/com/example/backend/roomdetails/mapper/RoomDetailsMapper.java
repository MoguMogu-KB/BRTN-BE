package com.example.backend.roomdetails.mapper;

import com.example.backend.roomdetails.dto.*;
import com.example.backend.roomlist.vo.RoomListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomDetailsMapper {

    // 12. 방 기본 정보 출력
    RoomDetailsDto getRoomBasicInfo(@Param("roomNum") int roomNum);

    // 13. 참여자 목록 출력
    List<ParticipantDto> getParticipantList(@Param("roomNum") int roomNum);

    // 14. 구독 계정 정보 출력
    SubscriptionAccountDto getSubscriptionAccountInfo(@Param("roomNum") int roomNum);

    // 15. 이번달 납부 현황 출력
    List<PaymentStatusDto> getMonthlyPaymentStatus(@Param("roomNum") int roomNum);

    // 16. 모임통장 거래 내역 출력
    List<TransactionDto> getAccountTransactionHistory(@Param("accountNumber") String accountNumber);

    // 17. 이번 달 회비 납부하기
    void insertTransaction(@Param("accountNumber") String accountNumber,
                           @Param("transactionDetails") String transactionDetails,
                           @Param("amount") int amount);

    //20 시작시 계정 추가
    int addAccount(
            @Param("roomNum")
            int roomNum,
            @Param("subscribeId")
            String subscribeId,
            @Param("subscribePwd")
            String subscribePwd);

    //21 계정 수정
    void updateAccount(@Param("roomNum") int roomNum, @Param("subscribeId") String subscribeId, @Param("subscribePwd") String subscribePwd);

    // 22. 비밀번호 랜덤 값 생성
    String randomPassword();

    // 27. 내가 참여 중인 방 목록 출력
    List<RoomListVO> selectMyRoomList(String id);


}
