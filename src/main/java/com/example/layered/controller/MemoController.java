package com.example.layered.controller;

import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import com.example.layered.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
Controller의 역할
- 클라이언트의 요청을 받는 역할을 수행한다.
- 요청에 대한 처리를 Service Layer에 전달한다.
- Service에서 처리 완료된 결과를 클라이언트에 응답한다.
 */

@RestController  // @Controller + @ResponseBody -> 응답할 데이터가 있는 Controller인 경우에 사용한다.
@RequestMapping("/memos")  // Prefix URL 설정
public class MemoController {

    /**
     * 주입된 의존성을 변경할 수 없어 객체의 상태를 안전하게 유지할 수 있다.
     * final 키워드를 사용하게 되면 최초로 설정된 MemoService가 애플리케이션이 종료될 때까지 계속 유지된다.
     */
    private final MemoService memoService;

    /**
     * 생성자 주입
     * 클래스가 필요로 하는 의존성을 생성자를 통해 전달하는 방식
     * @param memoService @Service로 등록된 MemoService 구현체인 Impl
     */
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    /**
     * 1. 요청 (Controller)
     * 파라미터 바인딩 중이다.
     * @RequestBody를 사용해서 HttpMessageConverter가 동작해서 JSON 데이터가 dto 형태로 변환된다.
     */
    /**
     * 메모 생성 API
     * @param : {@link MemoRequestDto} 메모 생성 요청 객체
     * @return : {@link ResponseEntity<MemoResponseDto>} JSON 응답
     */
    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {

        /**
         * 4. 응답 (Controller)
         * Service Layer를 호출하고 응답받는 역할을 추가한다.
         */
        return new ResponseEntity<>(memoService.saveMemo(dto), HttpStatus.CREATED);
    }

    /**
     2. 비지니스 로직
     MemoId 식별자 계산 (Repository)
     식별자가 1씩 증가 하도록 만듦
     이것은 원래 데이터베이스의 역할이다. -> Repository로 옮길 예정
     */

    /**
     요청받은 데이터로 Memo 객체 생성 (Service)
     생성한 Repository를 호출해서 저장하도록 만드는 것은 Service Layer의 영역이다. -> Service Layer로 옮길 예정
     */

    /**
     3. 데이터베이스 상호작용
     Inmemory DB에 Memo 저장 (Repository)
     데이터베이스와 실제로 상호작용하는 부분 -> Repository로 옮길 예정
     */
}


