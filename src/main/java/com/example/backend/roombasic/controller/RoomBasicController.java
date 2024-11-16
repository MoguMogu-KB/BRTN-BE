package com.example.backend.roombasic.controller;

import com.example.backend.roombasic.service.RoomBasicService;
import com.example.backend.roombasic.vo.RoomBasicVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("room/basic")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class RoomBasicController {

    private final RoomBasicService roomBasicService;

    // 1+2. 방 개설 - 상세 정보 추가 & 팀장 사용자 추가
    @PostMapping("/create")
    public String createRoom(@RequestBody RoomBasicVO roombasicVO, @RequestParam String creatorId) {
        try {
            roomBasicService.createRoom(roombasicVO, creatorId);
            return "create room successfully";
        } catch (Exception e) {
            return "room create error: " + e.getMessage();
        }
    }

    // 3. 방 세부 내용 변경 - 방번호, 계좌번호, 팀장은 못 바꿈
    @PutMapping("/update")
    public String updateRoomDetails(@RequestBody RoomBasicVO roomBasicVO) {
        try {
            roomBasicService.updateRoomBasic(roomBasicVO);
            return "update room successfully";
        } catch (Exception e) {
            return "room update error: " + e.getMessage();
        }
    }
}
