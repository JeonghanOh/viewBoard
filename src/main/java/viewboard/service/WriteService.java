package viewboard.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viewboard.dto.CommentDto;
import viewboard.dto.WriteDTO;
import viewboard.entity.BoardEntity;
import viewboard.entity.CommentEntity;
import viewboard.repository.DetailRepository;

@Service
@RequiredArgsConstructor
public class WriteService {
    @Autowired 
    DetailRepository writeRepository;
    public void save(WriteDTO writeDTO){
        BoardEntity boardEntity = new BoardEntity(writeDTO);
        writeRepository.save(boardEntity);
    }

    public BoardEntity getFindid(int id) {
        BoardEntity boardEntity = null;
        try {
            boardEntity = writeRepository.findByBoardId(id);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return boardEntity;
    }
}


