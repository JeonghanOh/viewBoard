package viewboard.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viewboard.dto.WriteDTO;
import viewboard.entity.BoardEntity;
import viewboard.repository.WriteRepository;

@Service
@RequiredArgsConstructor
public class WriteService {
    @Autowired WriteRepository writeRepository;
    public void save(WriteDTO writeDTO){
        int a = writeDTO.getBoardContent().length();
        String b = writeDTO.getBoardContent().substring(3,a-4);
        BoardEntity boardEntity = new BoardEntity(writeDTO);
        boardEntity.setBoardContent(b);
        writeRepository.save(boardEntity);
    }
}
