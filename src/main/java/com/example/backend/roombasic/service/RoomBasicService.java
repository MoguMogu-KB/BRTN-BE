package com.example.backend.roombasic.service;

import com.example.backend.roombasic.vo.RoomBasicVO;

public interface RoomBasicService {
    // 1+2. 방 개설 - 상세 정보 추가 & 팀장 사용자 추가
    public void createRoom(RoomBasicVO roomBasicVO, String creatorId);

    // 3. 방 세부 내용 변경
    public void updateRoomBasic(RoomBasicVO roomBasicVO);
}
