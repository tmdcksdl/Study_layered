package com.example.layered.dto;

import com.example.layered.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor  // 전체 필드를 가진 생성자는 없다. 그래서 전체 필드를 가진 생성자를 추가해준다.
public class MemoResponseDto {

    private Long id;
    private String title;
    private String contents;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
    }

}
