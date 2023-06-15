package viewboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminBoardDto {
    private List<Integer> boardType;
    private String boardTitle;
    private String boardTitle2;
    private String boardInfo;
}
