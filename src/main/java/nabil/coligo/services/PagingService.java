package nabil.coligo.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Ahmed Nabil
 */
@Service
public class PagingService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 100;
    /**
     * params: pageNumber is 1-indexed
     */
    public static Pageable getPageable(Integer pageNumber, Integer pageSize) {
        Integer queryPageNumber = DEFAULT_PAGE_NUMBER;
        Integer queryPageSize = DEFAULT_PAGE_SIZE;
        if(pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        }

        if(pageSize != null && pageSize > 0) {
            queryPageSize = pageSize;
        }
        return PageRequest.of(queryPageNumber, queryPageSize);
    }
}
