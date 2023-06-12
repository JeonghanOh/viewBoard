package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import viewboard.entity.UserEntity;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public boolean existsByUserEmail(String email);

    public UserEntity findByUserEmail(String id);
    public UserEntity findUserEmailByUserNameAndUserPhoneNumber(String name,String phoneNumber);
    public UserEntity findUserPasswordByUserEmailAndUserNameAndUserPhoneNumber(String email,String name,String phoneNumber);
    @Transactional
    public void deleteByUserEmail(String email);
    @Query(value = "select user_nickname from user where user_email = ?1", nativeQuery = true)
    public String findByUserNickname(String email);

    @Query(value="update user set user_nickname = ?1 where user_email = ?2",nativeQuery = true)
    @Modifying
    @Transactional
    public void FixNickname(String NickName, String email);

    @Query(value="update user set board_count = board_count + 1 where user_email = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    public void increaseCount(String email);

    @Query(value="update user set board_count = board_count - 1 where user_email = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    public void decreaseCount(String email);
}
