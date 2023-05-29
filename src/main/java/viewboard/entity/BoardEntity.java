package viewboard.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import viewboard.dto.CommentDto;
import viewboard.dto.WriteDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "boarddetail")
public class BoardEntity {
    @Id
    @Column(name="board_id")
    private int boardId;
    @Column(name="board_type")
    private int boardType;
    @Column(name="board_title")
    private String boardTitle;
    @Column(name="board_content")
    private String boardContent;
    @Column(name="board_image")
    private String boardImage;
    @Column(name="board_file")
    private String boardFile;
    @Column(name="board_like")
    private int boardLike;
    @Column(name="board_click")
    private int boardClick;
    @Column(name="user_email")
    private String userEmail;
    @Column(name="comment_count")
    private int commentCount;
    @CreationTimestamp
    @Column(name="board_date")
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
