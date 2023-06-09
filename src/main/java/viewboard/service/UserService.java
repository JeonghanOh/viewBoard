package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viewboard.dto.BoardTypeDto;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.UserEntity;
import viewboard.repository.BoardRepository;
import viewboard.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class UserService {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    public UserEntity userInfo(String email) {
        UserEntity user = userRepository.findByUserEmail(email);
        user.setUserPassword("");
        System.out.println(user);
        return user;
    }

    public List<BoardTypeDto> favBoard(String email) {
        List<BoardTypeEntity> list = boardRepository.favoriteBoard(email);
        List<BoardTypeDto> dtoList = new ArrayList<>();
        for (BoardTypeEntity entity : list) {
            BoardTypeDto dto = new BoardTypeDto();
            dto.setBoardName(entity.getBoardName());
            dto.setBoardType(entity.getBoardType());
            // 필드들에 대한 매핑 추가...

            dtoList.add(dto);
        }
        System.out.println(list);
        return dtoList;
    }


}
