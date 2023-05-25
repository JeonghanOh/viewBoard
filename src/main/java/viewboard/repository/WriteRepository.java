package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import viewboard.entity.BoardEntity;

@Repository
public interface WriteRepository extends JpaRepository<BoardEntity, Long> {
    // update board_table set board_hits = board_hits+1 where id = ?;
    // native를 하면 sql 실제 query를 쓸수 있음 @Query(value="update BoardEntity b set b.boardHits=b.boardHits+1 where b.id =:id", nativeQuery = true)

}