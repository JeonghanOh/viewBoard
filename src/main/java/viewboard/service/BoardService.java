package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;
import viewboard.repository.BoardRepository;
import viewboard.repository.DetailRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class BoardService {
    @Autowired DetailRepository detailRepository;
    @Autowired BoardRepository boardRepository;
    public List<BoardEntity> getList(int type){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        try{
            boardList = detailRepository.findTop10ByBoardTypeOrderByBoardIdDesc(type);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }

        return boardList;
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

}
