package viewboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import viewboard.dto.CommentDto;
import viewboard.dto.WriteDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @Column
    private int commentId;
    @Column
    private int boardId;
    @Column
    private String commentContent;
    @Column
    private String userEmail;

    public CommentEntity(CommentDto commentDto) {
        this.setCommentId(commentDto.getCommentId());
        this.setBoardId(commentDto.getBoardId());
        this.setCommentContent(commentDto.getCommentContent());
        this.setUserEmail(commentDto.getUserEmail());
    }
}
