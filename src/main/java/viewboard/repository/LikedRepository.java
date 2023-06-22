package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import viewboard.dto.LikedDto;
import viewboard.entity.LikedEntity;

import java.util.List;

@Repository
public interface LikedRepository extends JpaRepository <LikedEntity, LikedDto>{

    @Modifying
    @Query(value = "update boarddetail set board_like = board_like + 1 where board_id = :id", nativeQuery = true)
    public void Uplike(@Param("id") int id);
    @Transactional
    public void deleteByLikedDtoUserEmailIn(List<String> email);

    public void deleteByLikedDtoBoardIdAndLikedDtoUserEmail(int id ,String email);

    public void deleteByLikedDtoBoardId(int boardid);
    @Transactional
    public void deleteByLikedDtoUserEmail(String userEmail);
    @Transactional
    @Modifying
    @Query(value="delete from liky where board_id in ?1", nativeQuery = true)
    public void deleteLike(List<Integer> id);


    boolean existsByLikedDtoBoardIdAndLikedDtoUserEmail(int id, String email);
}
