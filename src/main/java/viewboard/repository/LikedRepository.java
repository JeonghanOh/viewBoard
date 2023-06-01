package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import viewboard.entity.LikedEntity;


@Repository
public interface LikedRepository extends JpaRepository <LikedEntity, Long>{

    @Query(value = "update boarddetail set board_like = board_like + 1 where board_id = :id", nativeQuery = true)
    @Modifying
    void likeupdate(@Param("id") int id);


}
