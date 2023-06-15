package viewboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import viewboard.dto.SearchuserDto;
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
    @Column(name="user_email")
    private String userEmail;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="user_name")
    private String userName;
    @Column(name="user_nickname")
    private String userNickName;
    @Column(name="user_phonenumber")
    private String userPhoneNumber;
    @Column(name="user_grant")
    private Integer userGrant;
    @Column(name="user_adult")
    private Integer userAdult;
    @Column(name = "board_count")
    private Integer boardCount;

    public UserEntity(SignUpDto dto) {
        this.userEmail = dto.getUserEmail();
        this.userPassword= dto.getUserPassword();
        this.userName = dto.getUserName();
        this.userNickName = dto.getUserNickname();
        this.userPhoneNumber = dto.getUserPhoneNumber();
    }
    public UserEntity(SearchuserDto searchuserDto) {
        this.userEmail = searchuserDto.getUser_email();
        this.userName = searchuserDto.getUser_name();
        this.userNickName = searchuserDto.getUser_nickname();
    }
}

