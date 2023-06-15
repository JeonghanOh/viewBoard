package viewboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import viewboard.entity.UserEntity;

@Repository
public interface AdminRepository extends JpaRepository<UserEntity, Integer> {
    // concat 해당 단어가 포함한 모든 것\
    @Query(value="select * from user where user_email Like CONCAT('%', ?1, '%')", nativeQuery = true)
    public Page<UserEntity> selectUser (String email , Pageable pageable);

    @Query(value="select * from user where user_name Like CONCAT('%', ?1, '%')", nativeQuery = true)
    public Page<UserEntity> selectUserName (String name, Pageable pageable);

    @Query(value="select * from user where user_nickname Like CONCAT('%', ?1, '%')", nativeQuery = true)
    public Page<UserEntity> selectUserNickName (String nickname, Pageable pageable);

}
