package viewboard.dto;

import lombok.*;
import viewboard.entity.BoardEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생서자
public class WriteDTO {
    private int boardType;
    private int boardId;
    private String boardTitle;
    private String boardContent;
    private String boardImage;
    private String boardFile;
    private int boardLike;
    private int boardClick;
    private String userEmail;
    private int commentCount;
    private LocalDateTime boardDate;

}
