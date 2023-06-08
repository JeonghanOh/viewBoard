package viewboard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import viewboard.dto.FavoriteDto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="favorite")
public class FavoriteEntity {
@EmbeddedId
    private FavoriteDto favoriteDto;
}
