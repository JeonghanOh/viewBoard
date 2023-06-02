package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import viewboard.dto.FavoriteDto;
import viewboard.dto.LikedDto;
import viewboard.dto.WriteDTO;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.FavoriteEntity;
import viewboard.entity.LikedEntity;
import viewboard.repository.BoardRepository;
import viewboard.repository.DetailRepository;
import viewboard.repository.FavoriteRepository;
import viewboard.repository.LikedRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class BoardService {
    @Autowired DetailRepository detailRepository;
    @Autowired BoardRepository boardRepository;
    @Autowired
    LikedRepository likedRepository;
    @Autowired
    FavoriteRepository favoriteRepository;
    public Page<BoardEntity> getList(int type, Pageable pageable){
        return detailRepository.findByBoardType(type,pageable);
    }
    public List<BoardEntity> getHotboard(int type){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        try{
            boardList = detailRepository.liveHot(type);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }

        return boardList;
    }
    public BoardEntity getDetail(int id){
        BoardEntity detail;
        try{
            detail = detailRepository.findByBoardId(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }
        return detail;
    }

    public Long counting(int type){
        long count = 0;
        try{
             count = detailRepository.countByBoardType(type);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }
        return count;
    }
    public BoardTypeEntity getBoardType(int type){
        BoardTypeEntity typeInfo;
        try{
            typeInfo = boardRepository.findByboardType(type);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }
        return typeInfo;
    }

    public List<BoardTypeEntity>HotBoardType(){
        List<BoardTypeEntity> list = boardRepository.getHotBoardName();
        return list;
    }

    public void increaseView(int id){
        detailRepository.UpView(id);
    }

    public void likeContent(LikedDto likedDto) {
        LikedEntity liked = new LikedEntity(likedDto);
        likedRepository.save(liked);
    }
    public void addFavorite(FavoriteDto dto){
        String email = dto.getUserEmail();
        int type = dto.getBoardType();

        if(favoriteRepository.existsByFavoriteDtoUserEmailAndFavoriteDtoBoardType(email,type)){
            favoriteRepository.deleteByFavoriteDtoUserEmailAndFavoriteDtoBoardType(email,type);
        }else{
            FavoriteEntity fav= new FavoriteEntity(dto);
            favoriteRepository.save(fav);
        }
    }
}

