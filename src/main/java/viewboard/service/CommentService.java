package viewboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viewboard.dto.CommentDto;
import viewboard.entity.CommentEntity;
import viewboard.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public void Commentinsert(CommentDto dto) {
        CommentEntity comment = new CommentEntity(dto);
        try {
            // 댓글 작성 DB에 저장
            commentRepository.save(comment);
        } catch (Exception error){
            error.printStackTrace();
        }
    }

    public CommentEntity getcomment(int Id) {
        CommentEntity commentEntity = null;
        try {
            commentEntity = commentRepository.findBycommentId(Id);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return commentEntity;
    }

    public int CommentCount(int Id) {
        int count = 0;
        try {
            count = commentRepository.selectCount(Id);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return count;
    }
}
