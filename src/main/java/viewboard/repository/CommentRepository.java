package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import viewboard.entity.CommentEntity;
import viewboard.entity.UserEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    public CommentEntity findBycommentId(int Id);
    @Query(value = "SELECT * FROM comment WHERE comment.board_id = :boardId order by comment_id desc limit 10", nativeQuery = true)
    public List<CommentEntity> findByCommentLists(@Param("boardId") int boardId);

    @Query(value = "SELECT * FROM comment WHERE comment.board_id = :boardId ORDER BY comment_id DESC LIMIT :pageSize OFFSET :offset", nativeQuery = true)
    public List<CommentEntity> findByCommentList(@Param("boardId") int boardId, @Param("offset") int offset, @Param("pageSize") int pageSize);

    @Query(value = "select Count(*) from comment where board_id =?1", nativeQuery = true)
    public int selectCount(int board_id);

    public void deleteByboardId(int id);
    @Transactional
    @Modifying
    @Query(value="delete from comment where board_id in ?1", nativeQuery = true)
    public void deleteComment(List<Integer> id);

    @Transactional
    public void deleteByUserEmail(String userEmail);

    @Transactional
    public void deleteByUserEmailIn(List<String> email);
}
