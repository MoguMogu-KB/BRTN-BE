package com.example.backend.roombasic.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RoomBasicVO {
    private int roomNum;
    private String accountNumber;
    private String title;
    private String category;
    private int numberTotal;
    private String period;
    private Date dueDate;
    
    // 팀장 ID는 따로 담지 않음

    // RoomComplete
    private int numberCurrent; // 현재 구성원 수 -> 1로 설정(팀장)
}
