package fan.service;

import fan.pojo.entity.CategoryDO;
import fan.pojo.query.CategoryQuery;

import java.util.List;

/**
 * Category interface
 *
 * @author Fan
 * @since 2024/2/22 17:21
 */
public interface CategoryService {

    /**
     * Retrieve category list
     *
     * @param categoryQuery {@link CategoryQuery}
     * @return {@link List<CategoryDO>}
     * @author Fan
     * @since 2024/3/14 11:30
     */
    List<CategoryDO> listCategories(CategoryQuery categoryQuery);
}