package viewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
