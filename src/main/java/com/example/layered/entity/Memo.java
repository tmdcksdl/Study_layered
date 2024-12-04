package com.example.layered.entity;

import com.example.layered.dto.MemoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Memo {

    // Respository Layer에서 식별자 값을 Setting해주기 위해 사용한다.
    private Long id;  // @Setter를 통해 id만 수정할 수 있도록 해준다. 클래스에 @Setter가 붙게 되면 전체 필드를 수정할 수 있다. 꼭 변경해야 되는 값만 @Setter 붙여줘야 한다.
    private String title;
    private String contents;

    /**
     * 제목과 내용만으로 Memo 객체를 생성할 수 있는 생성자 생성
     * @param title - 메모 제목
     * @param contents - 메모 내용
     */
    public Memo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    // 따로따로 입력해서 받을 수도 있다! 실제로 갈라놓으면 재사용성이 더 좋아진다. 테스트 코드 작성할 떄도 도움이 많이 된다.
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

}