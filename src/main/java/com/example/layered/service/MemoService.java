package com.example.layered.service;

import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;

import java.util.List;

public interface MemoService {
    /**
     *
     * @param dto
     * @return MemoResponseDto - 이렇게 해야 MemoController에서 반환 타입이 바로 MemoResponseDto로 들어갈 수 있다. 따로 변환할 필요가 없다.
     */
    MemoResponseDto saveMemo(MemoRequestDto dto);

    List<MemoResponseDto> findAllMemos();

    MemoResponseDto findMemoById(Long id);

    MemoResponseDto updateMemo(Long id, String title, String contents);

    MemoResponseDto updateTitle(Long id, String title, String contents);

    void deleteMemo(Long id);
}
