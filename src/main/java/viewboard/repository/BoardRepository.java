package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.FavoriteEntity;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardTypeEntity, Long> {
    public BoardTypeEntity findByboardType(int boardType);

    @Query(value="select max(board_type) from board", nativeQuery = true)
    public int getMaxBoard_type();

    @Query(value="select A.* from board as A join (select board_type, count(board_type) as count_board_type from boarddetail group by board_type)as B on A.board_type = B.board_type order by count_board_type desc limit 8",nativeQuery = true)
    public List<BoardTypeEntity> getHotBoardName();
    @Query(value = "select A.* from board as A join (select * from favorite where user_email = ?1)as B on A.board_type = B.board_type",nativeQuery = true)
    public List<BoardTypeEntity> favoriteBoard(String userEmail);

    @Query(value="select * from board", nativeQuery = true)
    public List<BoardTypeEntity> getAllBoardName();
}
