package com.example.layered.repository;

import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    /**
     * Memo를 저장하는 메서드
     * @param memo - id가 없는 상태로 Repository Layer에 전달된다.
     * @return Memo
     */
    MemoResponseDto saveMemo(Memo memo);

    List<MemoResponseDto> findAllMemos();

    Optional<Memo> findMemoById(Long id);

    int updateMemo(Long id, String title, String contents);

    void deleteMemo(Long id);
}
