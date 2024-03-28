package fan.query;

import lombok.Setter;

/**
 * Pagination basic query parameters
 *
 * @author Fan
 * @since 2024/3/28 11:28
 */
@Setter
public class PageQuery {

    private int currentPage;

    private int pageSize;

    public int getCurrentPage() {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        return currentPage;
    }

    public int getPageSize() {
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return pageSize;
    }
}