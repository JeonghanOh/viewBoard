package viewboard.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import viewboard.dto.CommentDto;
import viewboard.dto.WriteDTO;
import viewboard.entity.BoardEntity;
import viewboard.entity.CommentEntity;
import viewboard.repository.DetailRepository;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WriteService {
    @Value("${comm.uploadPath}")
    private String uploadPath;
    @Autowired 
    DetailRepository detailRepository;



    public BoardEntity writePost2(MultipartFile file, WriteDTO dto){
        String filename= UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        String imgPath=uploadPath+"/"+filename;
        dto.setBoardImage(imgPath);
       try{
           file.transferTo(new File(imgPath));
       }catch(Exception e) {
           e.printStackTrace();
       }
       BoardEntity board = new BoardEntity(dto);
       return detailRepository.save(board);
    }
    public BoardEntity writePost1(WriteDTO dto){
        BoardEntity board = new BoardEntity(dto);
        return  detailRepository.save(board);
    }
    public BoardEntity getFindid(int id) {
        BoardEntity boardEntity = null;
        try {
            boardEntity = detailRepository.findByBoardId(id);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return boardEntity;
    }

    public BoardEntity prevPage (int id, int type) {
        BoardEntity boardList=null;
        try {
            boardList = detailRepository.PrevPage(id, type);
        } catch(Exception error) {
            error.printStackTrace();
        }
        return boardList;
    }

    public BoardEntity nextPage (int id, int type) {
        BoardEntity boardList=null;
        try {
            boardList = detailRepository.NextPage(id, type);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return boardList;
    }
}


