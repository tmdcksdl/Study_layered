package com.example.layered.repository;

import com.example.layered.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Annotation @Repository는 @Component와 같다, Spring Bean으로 등록한다는 뜻.
 * Spring Bean으로 등록되면 다른 클래스에서 주입하여 사용할 수 있다.
 * 명시적으로 Repository Layer 라는것을 나타낸다.
 * DB와 상호작용하여 데이터를 CRUD하는 작업을 수행한다.
 */
@Repository
public class MemoRepositoryImpl implements MemoRepository{

    /**
     * MemoController의 자료구조 코드를 잘라내서 가져온 코드이다.
     * Repository Layer의 역할이 실제 데이터베이스와 상호작용해서 데이터를 CRUD하는 영역이기 때문이다.
     * 그래서 데이터베이스를 넣어준다.
     */
    private final Map<Long, Memo> memoList = new HashMap<>();
}
