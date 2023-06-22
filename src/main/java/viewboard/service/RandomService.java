package viewboard.service;

import org.springframework.stereotype.Service;
import viewboard.entity.BoardTypeEntity;
import viewboard.repository.BoardRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Service
public class RandomService {

    public Set<Integer> getGesipanSet(BoardRepository boardRepository) {
        List<BoardTypeEntity> boards = boardRepository.selectAllBoard();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4) {
            int index = (int) (Math.random() * boards.size());
            set.add(boards.get(index).getBoardType());
        }

        return set;
    }
}
