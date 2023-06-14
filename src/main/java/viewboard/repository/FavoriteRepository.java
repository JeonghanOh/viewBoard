package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import viewboard.dto.FavoriteDto;
import viewboard.entity.FavoriteEntity;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoriteDto> {
    public boolean existsByFavoriteDtoUserEmailAndFavoriteDtoBoardType(String userEmail, int boardType);
    public void deleteByFavoriteDtoUserEmailAndFavoriteDtoBoardType(String userEmail, int boardType);
    public void deleteByFavoriteDtoUserEmail(String userEmail);
}
