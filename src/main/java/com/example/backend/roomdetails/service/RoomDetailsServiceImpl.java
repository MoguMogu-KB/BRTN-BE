package com.example.backend.roomdetails.service;

import com.example.backend.roomdetails.dto.*;

import com.example.backend.roomdetails.mapper.RoomDetailsMapper;
import com.example.backend.roomdetails.vo.Transaction;
import com.example.backend.roomlist.vo.RoomListVO;
import com.example.backend.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {

    private final RoomDetailsMapper roomDetailsMapper;

    @Autowired
    public RoomDetailsServiceImpl(RoomDetailsMapper roomDetailsMapper) {
        this.roomDetailsMapper = roomDetailsMapper;
    }

    // 12. 방 기본 정보 출력
    @Override
    public RoomDetailsDto getRoomBasicInfo(int roomNum) {
        return roomDetailsMapper.getRoomBasicInfo(roomNum);
    }

    // 13. 참여자 목록 출력
    @Override
    public List<ParticipantDto> getParticipantList(int roomNum) {
        return roomDetailsMapper.getParticipantList(roomNum);
    }

    // 14. 구독 계정 정보 출력
    @Override
    public SubscriptionAccountDto getSubscriptionAccountInfo(int roomNum) {
        return roomDetailsMapper.getSubscriptionAccountInfo(roomNum);
    }

    // 15. 이번 달 납부 현황 출력
    @Override
    public List<PaymentStatusDto> getMonthlyPaymentStatus(int roomNum) {
        return roomDetailsMapper.getMonthlyPaymentStatus(roomNum);
    }

    // 16. 모임통장 거래 내역 출력
    @Override
    public List<TransactionDto> getAccountTransactionHistory(String accountNumber) {
        return roomDetailsMapper.getAccountTransactionHistory(accountNumber);
    }

    // 17. 이번 달 회비 납부하기 버튼
    @Override
    public void insertTransaction(String accountNumber, String transactionDetails, int amount) {
        roomDetailsMapper.insertTransaction(accountNumber, transactionDetails, amount);
    }
    //20 시작시 계정 추가
    @Override
    public void addAccount(int roomNum, String subscribeId, String subscribePwd) {
        // 계정 추가 (업데이트)
        int updatedRows = roomDetailsMapper.addAccount(roomNum, subscribeId, subscribePwd);

        // 업데이트된 행이 없으면 추가 불가능 상태 처리
        if (updatedRows == 0) {
            throw new IllegalArgumentException("해당 방 번호가 존재하지 않습니다: " + roomNum);
        }
    }

    //21 계정 수정
    public void updateAccount(int roomNum, String serviceId, String password) {
        roomDetailsMapper.updateAccount(roomNum, serviceId, password);
    }

    // 22. 비밀번호 랜덤 값 생성
    @Override
    public String randomPassword() {
        return roomDetailsMapper.randomPassword();
    }

    // 27. 내가 참여 중인 방 목록 출력
    @Override
    public List<RoomListVO> selectMyRoomList(String id) {
        return roomDetailsMapper.selectMyRoomList(id);
    }

}
