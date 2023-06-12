package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import viewboard.dto.DeleteConDto;
import viewboard.dto.FavoriteDto;
import viewboard.dto.LikedDto;
import viewboard.dto.WriteDTO;
import viewboard.entity.*;
import viewboard.repository.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class BoardService {
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    LikedRepository likedRepository;
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    public Page<BoardEntity> getList(int type, Pageable pageable) {
        return detailRepository.findByBoardType(type, pageable);
    }

    public List<BoardEntity> getHotboard(int type) {
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        try {
            boardList = detailRepository.liveHot(type);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }

        return boardList;
    }

    public Long counting(int type) {
        long count = 0;
        try {
            count = detailRepository.countByBoardType(type);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }
        return count;
    }

    public BoardTypeEntity getBoardType(int type) {
        BoardTypeEntity typeInfo;
        try {
            typeInfo = boardRepository.findByboardType(type);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }
        return typeInfo;
    }

    public List<BoardTypeEntity> HotBoardType() {
        List<BoardTypeEntity> list = boardRepository.getHotBoardName();
        return list;
    }

    public void increaseView(int id) {
        detailRepository.UpView(id);
    }


    public void likeContent(int id, String email) {
        if (likedRepository.existsByLikedDtoBoardIdAndLikedDtoUserEmail(id , email)) {
            detailRepository.downlike(id);
            likedRepository.deleteByLikedDtoBoardIdAndLikedDtoUserEmail(id, email);
            return;
        }
        try {
            LikedDto likedDto = new LikedDto(id, email);
            LikedEntity likedEntity = new LikedEntity(likedDto, LocalDateTime.now());
            likedRepository.save(likedEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }
    }

    @Transactional
    public void likeview(int id) {
        likedRepository.Uplike(id);
    }

    public boolean isLiked(int id, String email){
        if (likedRepository.existsByLikedDtoBoardIdAndLikedDtoUserEmail(id, email)){
            return false;
        } else {
            return true;
        }
    }

    public Page<BoardEntity> getMyBoardList(String email, Pageable pageable) {
        return detailRepository.findByUserEmail(email, pageable);
    }

    public void addFavorite(FavoriteDto dto) {
        String email = dto.getUserEmail();
        int type = dto.getBoardType();

        if (favoriteRepository.existsByFavoriteDtoUserEmailAndFavoriteDtoBoardType(email, type)) {
            favoriteRepository.deleteByFavoriteDtoUserEmailAndFavoriteDtoBoardType(email, type);
        } else {
            FavoriteEntity fav = new FavoriteEntity(dto);
            favoriteRepository.save(fav);
        }
    }

    public List<BoardTypeEntity> selectAllBoardType() {
        List<BoardTypeEntity> list = boardRepository.getAllBoardName();
        return list;
    }

    public boolean isFavorite(int type, UserEntity user) {
        String email = user.getUserEmail();
        boolean isfav = favoriteRepository.existsByFavoriteDtoUserEmailAndFavoriteDtoBoardType(email, type);
        return isfav;
    }

    public void deleteCon(DeleteConDto dto, UserEntity user){
        int id = dto.getBoardId();
        String userEmail = dto.getUserEmail();
        try {
            detailRepository.deleteByBoardId(id);
            likedRepository.deleteByLikedDtoBoardId(id);
            commentRepository.deleteByboardId(id);
            userRepository.decreaseCount(userEmail);
            user.setBoardCount(user.getBoardCount() - 1);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

