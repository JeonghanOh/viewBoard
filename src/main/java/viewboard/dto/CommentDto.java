package viewboard.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {
    private int commentId;
    private int boardId;
    private String commentContent;
    private String userEmail;

    public CommentDto(int commentId, int boardId, String commentContent, String userEmail) {
        this.commentId = commentId;
        this.boardId = boardId;
        this.commentContent = commentContent;
        this.userEmail = userEmail;
    }

}
