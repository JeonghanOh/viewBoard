package viewboard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import viewboard.entity.BoardEntity;
import viewboard.repository.DetailRepository;

import java.util.List;

@Service
public class SearchService {
    public Page<BoardEntity> searchResult(String query, DetailRepository writeRepository, Pageable pageable) {
        Page<BoardEntity> ret;

        query = query.replaceAll("\\s+", " ");
        query = query.trim();
        String[] tmp = query.split(" ");
        String val = "";
        for (int i = 0; i < tmp.length; i++) {
            val += tmp[i];
            if (i < tmp.length - 1) {
                val += "|";
            }
        }

        ret = writeRepository.getSearchResult(val, pageable);

        return ret;
    }
}