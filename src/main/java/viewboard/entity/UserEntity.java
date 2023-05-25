package viewboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import viewboard.dto.SignUpDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name="userEmail")
    private String userEmail;
    @Column(name="userPassword")
    private String userPassword;
    @Column(name="userName")
    private String userName;
    @Column(name="userNickname")
    private String userNickName;
    @Column(name="userPhoneNumber")
    private int userPhoneNumber;
    @Column(name="userGrant")
    private int userGrant;
    @Column(name="userAdult")
    private int userAdult;
    @Column(name = "boardCount")
    private int boardCount;

    public UserEntity(SignUpDto dto) {
        this.userEmail = dto.getUserEmail();
        this.userPassword= dto.getUserPassword();
        this.userName = dto.getUserName();
        this.userNickName = dto.getUserNickname();
        this.userPhoneNumber = dto.getUserPhoneNumber();
    }
}

