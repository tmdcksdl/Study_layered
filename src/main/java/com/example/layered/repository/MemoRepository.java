package com.example.layered.repository;

import com.example.layered.entity.Memo;

public interface MemoRepository {

    /**
     * Memo를 저장하는 메서드
     * @param memo - id가 없는 상태로 Repository Layer에 전달된다.
     * @return Memo
     */
    Memo saveMemo(Memo memo);
}
