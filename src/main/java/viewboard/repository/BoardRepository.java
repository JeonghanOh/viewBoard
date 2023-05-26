package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardTypeEntity, Long> {
    public BoardTypeEntity findByboardType(int boardType);

}
