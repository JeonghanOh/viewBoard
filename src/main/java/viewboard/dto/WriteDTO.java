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

    public static WriteDTO toBoardDTO(BoardEntity boardEntity){
        WriteDTO writeDTO = new WriteDTO();
        writeDTO.setBoardType(boardEntity.getBoardType());
        writeDTO.setBoardId(boardEntity.getBoardId());
        writeDTO.setBoardTitle(boardEntity.getBoardTitle());
        writeDTO.setBoardContent(boardEntity.getBoardContent());
        writeDTO.setBoardImage(boardEntity.getBoardImage());
        writeDTO.setBoardFile(boardEntity.getBoardFile());
        writeDTO.setBoardLike(boardEntity.getBoardLike());
        writeDTO.setBoardClick(boardEntity.getBoardClick());
        writeDTO.setUserEmail(boardEntity.getUserEmail());
        writeDTO.setCommentCount(boardEntity.getCommentCount());
        writeDTO.setBoardDate(boardEntity.getBoardDate());
        return writeDTO;
    }
}
