package fan.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.pojo.entity.NavDO;
import fan.pojo.query.NavQuery;

import java.util.List;

/**
 * Resource navigation interface
 *
 * @author Fan
 * @since 2024/3/27 10:54
 */
public interface NavService {

    /**
     * Paginate navs retrieval
     *
     * @param navQuery {@link NavQuery}
     * @return {@link Page<NavDO>}
     * @author Fan
     * @since 2024/3/28 16:31
     */
    Page<NavDO> pageNavs(NavQuery navQuery);

    /**
     * Add nav
     *
     * @param navDO {@link NavDO}
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/28 16:32
     */
    Integer addNav(NavDO navDO);

    /**
     * Update nav
     *
     * @param navDO {@link NavDO}
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/28 16:33
     */
    Integer updateNav(NavDO navDO);

    /**
     * Batch delete navs via ID list
     *
     * @param ids ID list
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/28 16:33
     */
    Integer batchDeleteNavs(List<String> ids);
}