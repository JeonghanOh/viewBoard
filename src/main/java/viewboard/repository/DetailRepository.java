package viewboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<BoardEntity, Long> {
    public Page<BoardEntity> findByBoardType(int boardType, Pageable pageable);
//    public List<BoardEntity> findByBoardType(int boardType, Pageable pageable);
    public BoardEntity findByBoardId(int id);
    public Page<BoardEntity> findByUserEmail(String email, Pageable pageable);
    public long countByBoardType(int boardType);

    @Query(value = "select * from boarddetail where board_id < :id and board_type = :type order by board_id desc limit 1", nativeQuery = true)
    public BoardEntity PrevPage(@Param("id") int id, @Param("type") int type);

    @Query(value = "select * from boarddetail where board_id > :id and board_type = :type limit 1", nativeQuery = true)
    public BoardEntity NextPage(@Param("id") int id, @Param("type") int type);
    @Query(value="select A.* from boarddetail as A join (select count(board_id) as count, board_id from liky as B where like_time >= date_sub(now(), INTERVAL 4 HOUR) group by board_id order by count(board_id) desc LIMIT 10) B on A.board_id = B.board_id where board_type=?1",nativeQuery = true)
    public List<BoardEntity> liveHot(int boardType);

    @Query(value="select * from boarddetail where board_type = :type ORDER BY board_id DESC LIMIT 5", nativeQuery = true)
    public List<BoardEntity> findByboardType(@Param("type") int type);

    @Query(value="select A.* from boarddetail as A join (select count(board_id) as count, board_id from liky as B where like_time >= date_sub(now(), INTERVAL 4 HOUR) group by board_id order by count(board_id) desc LIMIT 10) B on A.board_id = B.board_id", nativeQuery = true)
    public List<BoardEntity> getHotGesigeul();
    @Query(value="SELECT * FROM boarddetail WHERE board_title regexp(:keyword)", nativeQuery = true)
    public Page<BoardEntity> getSearchResult(@Param("keyword") String keyword, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "update boarddetail set board_click = board_click + 1 where board_id =?1",nativeQuery = true)
    public void UpView(int id);

    @Modifying
    @Transactional
    @Query(value = "update boarddetail set board_like = board_like - 1 where board_id = ?1", nativeQuery = true)
    public void downlike(int boardId);
}