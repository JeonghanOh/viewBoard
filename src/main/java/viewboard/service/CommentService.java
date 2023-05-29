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
            commentRepository.save(comment);
        }

        public CommentEntity getcomment(int Id){
            CommentEntity commentEntity = null;
            try {
                commentEntity = commentRepository.findBycommentId(Id);
            } catch (Exception error) {
                error.printStackTrace();
            }
            return commentEntity;
        }
    }
