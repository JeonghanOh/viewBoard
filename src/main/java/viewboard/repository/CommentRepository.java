package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import viewboard.entity.CommentEntity;
import viewboard.entity.UserEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    public CommentEntity findBycommentId(int Id);
    @Query(value = "SELECT * FROM comment WHERE comment.board_id = :boardId order by comment_id desc limit 10", nativeQuery = true)
    public List<CommentEntity> findByCommentLists(@Param("boardId") int boardId);

    @Query(value = "SELECT * FROM comment WHERE comment.board_id = :boardId ORDER BY comment_id DESC LIMIT :pageSize OFFSET :offset", nativeQuery = true)
    public List<CommentEntity> findByCommentList(@Param("boardId") int boardId, @Param("offset") int offset, @Param("pageSize") int pageSize);
}
