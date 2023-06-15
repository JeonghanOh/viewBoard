package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import viewboard.dto.AdminBoardDto;
import viewboard.dto.SearchuserDto;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.UserEntity;
import viewboard.repository.AdminRepository;
import viewboard.repository.BoardRepository;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    BoardRepository boardRepository;
    public Page<UserEntity> getUserList(SearchuserDto searchuserDto , Pageable pageable){
        UserEntity user = new UserEntity(searchuserDto);
        System.out.println("user" + user);
        Page<UserEntity> userPage = null;
        if (user.getUserEmail() != null && user.getUserEmail() != "") {
            userPage = adminRepository.selectUser(user.getUserEmail() , pageable);
        }
        if (user.getUserName() != null && user.getUserEmail() != "") {
            userPage = adminRepository.selectUserName(user.getUserName(), pageable);
        }
        if (user.getUserNickName() != null && user.getUserEmail() != "") {
            userPage = adminRepository.selectUserNickName(user.getUserNickName(), pageable);
        }
        System.out.println("userPage" + userPage);
        return userPage;
    }
    public Page<BoardTypeEntity> getBoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);

    }

    public void deleteBoard(List<Integer> list) {
        boardRepository.deleteByBoardTypeIn(list);
    }

    public void addBoard(AdminBoardDto dto) {
        BoardTypeEntity board = new BoardTypeEntity(dto);
        if (!boardRepository.existsByBoardName(dto.getBoardTitle())) {
            try {
                boardRepository.save(board);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public boolean editTitle(AdminBoardDto dto) {
        String title = dto.getBoardTitle();
        String edit = dto.getBoardTitle2();
        boolean a = false;
        if (boardRepository.existsByBoardName(title)) {
            try {
                boardRepository.changeTitle(title, edit);
                a = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return a;
    }
}
