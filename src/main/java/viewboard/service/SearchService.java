package viewboard.service;

import viewboard.entity.BoardEntity;
import viewboard.repository.DetailRepository;

import java.util.List;

public class SearchService {
    public List<BoardEntity> searchResult(String query, DetailRepository writeRepository){
        List<BoardEntity> ret;

        query = query.replaceAll("\\s+", " ");
        query = query.trim();
        String[] tmp = query.split(" ");
        String val = "";
        for(int i=0;i<tmp.length;i++){
            val += tmp[i];
            if(i < tmp.length - 1){
                val += "|";
            }
        }

        ret = writeRepository.getSearchResult(val);

        return ret;
    }
}
