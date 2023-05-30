package viewboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    @Email
    @NotEmpty(message = "이메일 입력은 필수 입니다.")
    private String userEmail;
    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String userPassword;
    private String userPasswordChk;
    @NotEmpty(message = "이름 입력은 필수 입니다.")
    private String userName;
    @NotEmpty(message = "닉네임 입력은 필수 입니다.")
    private String userNickname;
    @NotEmpty(message = "전화번호 입력은 필수 입니다.")
    private String userPhoneNumber;
}