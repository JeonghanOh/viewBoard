package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import viewboard.dto.BoardTypeDto;
import viewboard.dto.EditDto;
import viewboard.dto.FavoriteDto;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.FavoriteEntity;
import viewboard.entity.UserEntity;
import viewboard.repository.BoardRepository;
import viewboard.repository.DetailRepository;
import viewboard.repository.FavoriteRepository;
import viewboard.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class UserService {
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    UserRepository userRepository;
    public List<BoardEntity> myList(String email){
        List<BoardEntity> list = detailRepository.findByUserEmail(email);
        System.out.println(list);
        return list;
    }
    public UserEntity userInfo(String email){
        UserEntity user = userRepository.findByUserEmail(email);
        user.setUserPassword("");
        System.out.println(user);
        return user;
    }
    public List<BoardTypeDto> favBoard(String email){
        List<BoardTypeEntity> list =boardRepository.favoriteBoard(email) ;
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
    public void changeName(EditDto dto){
        String email = dto.getUserEmail();
        String nickname = dto.getUserNickName();
        userRepository.NickNameUpdate(nickname,email);
    }


}
