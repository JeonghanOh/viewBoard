package viewboard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import viewboard.dto.LikedDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@Table(name = "liky")
public class LikedEntity {
    @Id
    @Column
    private int boardId;

    @Column
    private String userEmail;

    @Column
    private LocalDateTime likeTime;

    public LikedEntity(LikedDto likedDto) {
        this.userEmail = likedDto.getUserEmail();
        this.boardId = likedDto.getBoardId();
        this.likeTime = likedDto.getLikeTime();
    }
}
