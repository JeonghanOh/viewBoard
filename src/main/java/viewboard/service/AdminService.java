package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import viewboard.dto.AdminBoardDto;
import viewboard.dto.SearchuserDto;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.SearchEntity;
import viewboard.repository.*;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    LikedRepository likedRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    FavoriteRepository favoriteRepository;
    public Page<SearchEntity> getUserList(SearchuserDto searchuserDto , Pageable pageable){
        SearchEntity user = new SearchEntity(searchuserDto);
        System.out.println("user" + user);
        Page<SearchEntity> userPage = null;
        if (user.getUserEmail() != null && user.getUserEmail() != "") {
            userPage = adminRepository.selectUser(user.getUserEmail() , pageable);
        }
        if (user.getUserName() != null && user.getUserName() != "") {
            userPage = adminRepository.selectUserName(user.getUserName(), pageable);
        }
        if (user.getUserNickName() != null && user.getUserNickName() != "") {
            userPage = adminRepository.selectUserNickName(user.getUserNickName(), pageable);
        }
        System.out.println("userPage" + userPage);
        return userPage;
    }

    public void updategrant(List<String> list){
        adminRepository.UpdateGrant(list);
    }

    public void deleteuser(List<String> list){
        // 유저 와 관련된 좋아요 정보, 게시글 정보, 댓글 정보, 선호 게시글 정보 등 전부 삭제
        adminRepository.deleteByUserEmailIn(list);
        userRepository.deleteByUserEmailIn(list);
        detailRepository.deleteByUserEmailIn(list);
        likedRepository.deleteByLikedDtoUserEmailIn(list);
        commentRepository.deleteByUserEmailIn(list);
        favoriteRepository.deleteByFavoriteDtoUserEmailIn(list);
    }

    public void rollbackgrant(List<String> list){
        // Admin 권한 뺏기
        adminRepository.RollbackGrant(list);
    }

    public Page<BoardTypeEntity> getBoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);

    }

    public void deleteBoard(List<Integer> list) {
        List<Integer> id = detailRepository.findBoardCon(list);
        boardRepository.deleteByBoardTypeIn(list);
        detailRepository.deleteBoardCon(list);
        likedRepository.deleteLike(id);
        commentRepository.deleteComment(id);
        favoriteRepository.deleteBytype(list);

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

    public boolean isGranted(String userEmail){
        int x = adminRepository.getGrantUser(userEmail);
        if(x == 0)
            return false;
        else
            return true;
    }
}
