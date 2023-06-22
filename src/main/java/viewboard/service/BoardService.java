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
        //existsByLikedDtoBoardIdAndLikedDtoUserEmail 복합키 BoardI와 UserEmail의 중복 체크 false , true 형태 반환
        if (likedRepository.existsByLikedDtoBoardIdAndLikedDtoUserEmail(id , email)) {
            // 좋아요 수를 낮추고 중복체크된 복합키 삭제
            detailRepository.downlike(id);
            likedRepository.deleteByLikedDtoBoardIdAndLikedDtoUserEmail(id, email);
            // 해당 복합키 가 존재 할경우 해당 함수 종료
            return;
        }
        try {
            LikedDto likedDto = new LikedDto(id, email);
            LikedEntity likedEntity = new LikedEntity(likedDto, LocalDateTime.now());
            // 위 중복체크에서 복합키가 존재하 않을경우 DB에 저장
            likedRepository.save(likedEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }
    }

    // 게시글에 좋아요 수를 올림
    @Transactional
    public void likeview(int id) {
        likedRepository.Uplike(id);
    }

    // 좋아요 상태 체크
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

    public List<BoardEntity> findById(int id) {
        List<BoardEntity> boardentity = null;
        try {
            boardentity = detailRepository.findByboardId(id);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return boardentity;
    }
    public int count(String email) {
        int count = 0;
        try{
          count = detailRepository.boardcount(email);
        }catch (Exception error){
            error.printStackTrace();
        }
        return count;
    }

}

