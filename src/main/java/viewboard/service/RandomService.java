package viewboard.service;

import viewboard.repository.BoardRepository;

import java.util.HashSet;
import java.util.Set;

public class RandomService {

    public Set<Integer> getGesipanSet(BoardRepository boardRepository){
        int maxType = boardRepository.getMaxBoard_type();
        Set<Integer> set = new HashSet<Integer>();
        while(set.size() < 4){
            int type = (int)(Math.random()*maxType)+1;
            set.add(type);
            System.out.println("type : " + type);
        }

        return set;
    }
}
