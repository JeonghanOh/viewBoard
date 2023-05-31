package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;
import viewboard.repository.BoardRepository;
import viewboard.repository.DetailRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class BoardService {
    @Autowired DetailRepository detailRepository;
    @Autowired BoardRepository boardRepository;
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
}
