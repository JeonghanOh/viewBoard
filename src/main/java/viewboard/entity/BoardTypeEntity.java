package viewboard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import viewboard.dto.AdminBoardDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class BoardTypeEntity {
    @Id
    @Column(name = "board_type")
    private int boardType;
    @Column(name = "board_name")
    private String boardName;
    @Column(name = "board_intro")
    private String boardIntro;
    public BoardTypeEntity(AdminBoardDto dto){
        this.boardName=dto.getBoardTitle();
        this.boardIntro = dto.getBoardInfo();
    }
}
