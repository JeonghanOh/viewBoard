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
import viewboard.entity.UserEntity;
import viewboard.repository.DetailRepository;
import viewboard.repository.UserRepository;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WriteService {
    // 저장 경로 해당 위치
    @Value("${comm.uploadPath}")
    private String uploadPath;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    UserRepository userRepository;


    // 파일이 있을 경우 작성
    public BoardEntity writePost2(MultipartFile file, WriteDTO dto) {
        // OriginalFileName과 랜덤 이름 사이에 '-' 추가해서 이미지 저장
        // 저장 파일 경로 지정 해줘야함
        String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        String imgPath = uploadPath + "/" + filename;
        dto.setBoardImage(imgPath);
        try {
            file.transferTo(new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        BoardEntity board = new BoardEntity(dto);
        return detailRepository.save(board);
    }

    // 게시글 작성
    public BoardEntity writePost1(WriteDTO dto) {
        BoardEntity board = new BoardEntity(dto);
        return detailRepository.save(board);
    }

    // 게시글 업데이트
    public void updatePost1(WriteDTO dto) {
        BoardEntity board = new BoardEntity(dto);
        detailRepository.boardUpdate(board.getBoardTitle(), board.getBoardContent(),board.getBoardType(),board.getBoardId());
    }


    // 해당 ID 를 가진 게시글 조회
    public BoardEntity getFindid(int id) {
        BoardEntity boardEntity = null;
        try {
            boardEntity = detailRepository.findByBoardId(id);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return boardEntity;
    }
    
    // 이전글 정보
    public BoardEntity prevPage(int id, int type) {
        BoardEntity boardList = null;
        try {
            boardList = detailRepository.PrevPage(id, type);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return boardList;
    }

    // 다음글 정보 
    public BoardEntity nextPage(int id, int type) {
        BoardEntity boardList = null;
        try {
            boardList = detailRepository.NextPage(id, type);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return boardList;
    }

    // 조회수 + 1
    public void increaseCount(WriteDTO dto, UserEntity user) {
        try {
            user.setBoardCount(user.getBoardCount() + 1);
            userRepository.increaseCount(dto.getUserEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


