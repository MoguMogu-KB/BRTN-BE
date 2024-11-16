package com.example.backend.account.mapper;

import com.example.backend.account.vo.AccountVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    // 1. 모임통장 개설 (새로운 모임 통장 계좌번호 추가)
    public void insertSharingAccount(AccountVO sharingAccount);


    // 2.
}
