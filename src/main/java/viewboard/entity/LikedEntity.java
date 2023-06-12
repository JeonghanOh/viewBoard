package viewboard.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import viewboard.dto.LikedDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@Table(name = "liky")
public class LikedEntity {
    @EmbeddedId
    private LikedDto likedDto;

    @Column
    private LocalDateTime likeTime;

}

