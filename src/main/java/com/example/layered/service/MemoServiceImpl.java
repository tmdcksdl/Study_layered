package com.example.layered.service;

import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import com.example.layered.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Annotation @Service는 @Component와 같다, Spring Bean으로 등록한다는 뜻.
 * Spring Bean으로 등록되면 다른 클래스에서 주입하여 사용할 수 있다.
 * 명시적으로 Service Layer 라는것을 나타낸다.
 * 비지니스 로직을 수행한다.
 */
@Service
public class MemoServiceImpl implements MemoService{

    // 항상 이 구조를 가져가니까 잘 기억해둬야 한다.
    private final MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public MemoResponseDto saveMemo(MemoRequestDto dto) {

        // 요청받은 데이터로 Memo 객체 생성 -> 식별자인 ID 값은 없다.
        Memo memo = new Memo(dto.getTitle(), dto.getContents());

        // DB 저장
        return memoRepository.saveMemo(memo);
    }

    @Override
    public List<MemoResponseDto> findAllMemos() {

        // 리스트 형태의 MemoResponseDto를 바로 반환받는다.
        List<MemoResponseDto> allMemos = memoRepository.findAllMemos();

        // return으로 바로 memoRepository.findAllMemos()를 받아도 상관없다. 변수에 저장해서 반환하느냐, 바로 반환하느냐의 차이이다.
        return allMemos;
    }

    @Override
    public MemoResponseDto findMemoById(Long id) {

        Memo memo = memoRepository.findMemoByIdOrElseThrow(id);

        return new MemoResponseDto(memo);
    }

    @Transactional
    @Override
    public MemoResponseDto updateMemo(Long id, String title, String contents) {

        // 필수값 검증
        if (title == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        // memo 수정
        int updatedRow = memoRepository.updateMemo(id, title, contents);

        // 수정된 row가 0개라면
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        Memo memo = memoRepository.findMemoByIdOrElseThrow(id);

        // 수정된 메모 조회
        return new MemoResponseDto(memo);
    }

    @Transactional
    @Override
    public MemoResponseDto updateTitle(Long id, String title, String contents) {

        if (title == null || contents != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is required values.");
        }

        int updatedRow = memoRepository.updateTitle(id, title);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Memo memo = memoRepository.findMemoByIdOrElseThrow(id);

        return new MemoResponseDto(memo);
    }

    @Override
    public void deleteMemo(Long id) {

        int deletedRow = memoRepository.deleteMemo(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }
}
