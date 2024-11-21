package com.example.backend.roominout.controller;

import com.example.backend.exception.CustomNotFoundException;
import com.example.backend.roominout.service.RoomInOutService;
import com.example.backend.roomlist.service.RoomListService;
import com.example.backend.roomlist.vo.RoomListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomInOutController {
    private final RoomInOutService roomInOutService;

    public RoomInOutController(RoomInOutService roomInOutService) {
        this.roomInOutService = roomInOutService;
    }

    // 10. 방 현재 구성원 수 증가
    // 11. 사용자 목록에 정보 추가
    @PostMapping("/add/person/{roomNum}")
    public ResponseEntity<String> insertPersonList(@PathVariable int roomNum, @RequestParam String id) {
        try {
            roomInOutService.increaseCurrntNumber(roomNum);

            log.info("Inserting with params: roomNum = {}, id = {}", roomNum, id);
            Map<String, Object> params = new HashMap<>();
            params.put("roomNum", roomNum);
            params.put("id", id);

            roomInOutService.insertPersonList(params);
            return ResponseEntity.status(HttpStatus.OK).body("Member count increased and user added successfully.");
        } catch (CustomNotFoundException e) {
            log.info("404 Error : RoomNum or id not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to add person.");
        } catch (Exception e) {
            log.info("500 Error : An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add person.");
        }
    }

    // 18. 방 현재 구성원 수 감소
    @PutMapping("/decrease/member/{roomNum}")
    public ResponseEntity<String> decreaseCurrntNumber(@PathVariable int roomNum) {
        try {
            roomInOutService.decreaseCurrntNumber(roomNum);
            return ResponseEntity.status(HttpStatus.OK).body("Member count decreased successfully.");
        } catch (CustomNotFoundException e) {
            log.info("404 Error : RoomNum not found by {}", roomNum);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to decrease member count.");
        } catch (Exception e) {
            log.info("500 Error : An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to decrease member count.");
        }
    }

    // 19. 사용자 목록에서 삭제
    @DeleteMapping("/delete/person")
    public ResponseEntity<String> deletePersonList(@RequestParam int roomNum, @RequestParam String id) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("roomNum", roomNum);
            params.put("id", id);

            roomInOutService.deletePersonList(params);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted person list successfully.");
        } catch (CustomNotFoundException e) {
            log.info("404 Error : RoomNum or id not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete person.");
        } catch (Exception e) {
            log.info("500 Error : An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete person.");
        }
    }
}
