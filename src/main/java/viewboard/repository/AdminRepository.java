package viewboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import viewboard.entity.SearchEntity;
import viewboard.entity.UserEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<SearchEntity, Integer> {
    // concat 해당 단어가 포함한 모든 것\
    @Query(value="select * from user where user_email Like CONCAT('%', ?1, '%')", nativeQuery = true)
    public Page<SearchEntity> selectUser (String email , Pageable pageable);

    @Query(value="select * from user where user_name Like CONCAT('%', ?1, '%')", nativeQuery = true)
    public Page<SearchEntity> selectUserName (String name, Pageable pageable);

    @Query(value="select * from user where user_nickname Like CONCAT('%', ?1, '%')", nativeQuery = true)
    public Page<SearchEntity> selectUserNickName (String nickname, Pageable pageable);

    @Transactional
    public void deleteByUserEmailIn(List<String> list);


    @Modifying
    @Transactional
    @Query(value="update user set user_grant = 1 where user_email in ?1", nativeQuery = true)
    public void UpdateGrant(List<String> list);

    @Modifying
    @Transactional
    @Query(value="update user set user_grant = 0 where user_email in ?1", nativeQuery = true)
    public void RollbackGrant(List<String> list);

    @Query(value="select user_grant from user where user_email=?1", nativeQuery = true)
    public int getGrantUser(String userEmail);
}
