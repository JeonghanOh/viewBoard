package viewboard.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viewboard.dto.WriteDTO;
import viewboard.entity.BoardEntity;
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
}
