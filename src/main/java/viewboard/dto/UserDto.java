package viewboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String UserEmail;
    private String UserNickname;
    private String UserPhonenumber;
    private int BoardCount;

}