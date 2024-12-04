package com.example.layered.controller;

import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
Controller의 역할
- 클라이언트의 요청을 받는 역할을 수행한다.
- 요청에 대한 처리를 Service Layer에 전달한다.
- Service에서 처리 완료된 결과를 클라이언트에 응답한다.
 */

@RestController
@RequestMapping("/memos")
public class MemoController {

    // Controller의 데이터베이스 (Repository) - 자료구조
    private final Map<Long, Memo> memoList = new HashMap<>();

    /*
    1. 요청 (Controller)
    파라미터 바인딩 중이다.
    @RequestBody를 사용해서 HttpMessageConverter가 동작해서 JSON 데이터가 dto 형태로 변환된다.
     */
    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {

        /*
        2. 비지니스 로직
        MemoId 식별자 계산 (Repository)
        식별자가 1씩 증가 하도록 만듦
        이것은 원래 데이터베이스의 역할이다. -> Repository로 옮길 예정
         */
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        /*
        요청받은 데이터로 Memo 객체 생성 (Service)
        생성한 Repository를 호출해서 저장하도록 만드는 것은 Service Layer의 영역이다. -> Service Layer로 옮길 예정
         */
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());

        /*
        3. 데이터베이스 상호작용
        Inmemory DB에 Memo 저장 (Repository)
        데이터베이스와 실제로 상호작용하는 부분 -> Repository로 옮길 예정
         */
        memoList.put(memoId, memo);

        // 4. 응답 (Controller)
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }
}
