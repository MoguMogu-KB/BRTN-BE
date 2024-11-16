package com.example.backend.roombasic.service;

import com.example.backend.roombasic.mapper.RoomBasicMapper;
import com.example.backend.roombasic.vo.RoomBasicVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoomBasicServiceImpl implements RoomBasicService {

    private final RoomBasicMapper roomBasicMapper;

    // 1+2. 방 개설 - 상세 정보 추가 & 팀장 사용자 추가
    @Override
    public void createRoom(RoomBasicVO roomBasicVO, String creatorId) {
        // 1. RoomList에 방 정보 추가
        roomBasicMapper.insertRoom(roomBasicVO);

        // 2. 방 생성자를 팀장으로 PersonList에 추가
        Map<String, Object> params = new HashMap<>();
        params.put("roomNum", roomBasicVO.getRoomNum());
        params.put("id", creatorId);
        roomBasicMapper.insertTeamLeader(params);
    }

    // 3. 방 세부 내용 변경
    @Override
    public void updateRoomBasic(RoomBasicVO roomBasicVO) {
        roomBasicMapper.updateRoomBasic(roomBasicVO);
    }
}
