package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import viewboard.dto.FavoriteDto;
import viewboard.entity.FavoriteEntity;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoriteDto> {
    public boolean existsByFavoriteDtoUserEmailAndFavoriteDtoBoardType(String userEmail, int boardType);
    public void deleteByFavoriteDtoUserEmailAndFavoriteDtoBoardType(String userEmail, int boardType);
    public void deleteByFavoriteDtoUserEmail(String userEmail);
    @Transactional
    @Modifying
    @Query(value="delete from favorite where board_type in ?1", nativeQuery = true)
    public void deleteBytype(List<Integer>type);
    @Transactional
    public void deleteByFavoriteDtoUserEmailIn(List<String> email);
}
