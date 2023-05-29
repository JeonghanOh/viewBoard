package viewboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindDto {
    private String userName;
    private String userPhonenumber;
    private String userEmail;
}
