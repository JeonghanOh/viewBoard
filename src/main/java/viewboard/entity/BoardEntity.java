package viewboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import viewboard.dto.WriteDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "boarddetail")
public class BoardEntity {
    @Id
    @Column
    private int boardId;
    @Column
    private int boardType;
    @Column
    private String boardTitle;
    @Column
    private String boardContent;
    @Column
    private String boardImage;
    @Column
    private String boardFile;
    @Column
    private int boardLike;
    @Column
    private int boardClick;
    @Column
    private String userEmail;
    @Column
    private int commentCount;
    @CreationTimestamp
    @Column
    private LocalDateTime boardDate;

    public BoardEntity (WriteDTO writeDTO) {
        this.setBoardType(writeDTO.getBoardType());
        this.setBoardId(writeDTO.getBoardId());
        this.setBoardTitle(writeDTO.getBoardTitle());
        this.setBoardContent(writeDTO.getBoardContent());
        this.setBoardImage(writeDTO.getBoardImage());
        this.setBoardFile(writeDTO.getBoardFile());
        this.setBoardLike(writeDTO.getBoardLike());
        this.setBoardClick(writeDTO.getBoardClick());
        this.setUserEmail(writeDTO.getUserEmail());
        this.setCommentCount(writeDTO.getCommentCount());
        this.setBoardDate(writeDTO.getBoardDate());
    }
}
