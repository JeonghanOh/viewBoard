package viewboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchuserDto {
    private String user_email;
    private String user_name;
    private String user_nickname;

}
