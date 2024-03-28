package fan.service;

import fan.pojo.entity.MenuDO;
import fan.pojo.query.MenuQuery;

import java.util.List;
import java.util.Set;

/**
 * Menu interface
 *
 * @author Fan
 * @since 2024/2/23 9:38
 */
public interface MenuService {

    /**
     * Retrieve navigation menu list
     *
     * @param userId User ID
     * @return {@link List<MenuDO>}
     * @author Fan
     * @since 2024/3/26 11:48
     */
    List<MenuDO> listNavMenus(String userId);

    /**
     * Retrieve routing menu list
     *
     * @param userId User ID
     * @return {@link List<MenuDO>}
     * @author Fan
     * @since 2024/3/26 11:48
     */
    List<MenuDO> listRouteMenus(String userId);

    /**
     * Retrieve menu list
     *
     * @param menuQuery {@link MenuQuery}
     * @return {@link List<MenuDO>}
     * @author Fan
     * @since 2024/3/14 11:40
     */
    List<MenuDO> listMenus(MenuQuery menuQuery);

    /**
     * Retrieve sub-menu list by parent ID
     *
     * @param parentId parent ID
     * @return {@link List<MenuDO>}
     * @author Fan
     * @since 2024/3/14 11:41
     */
    List<MenuDO> listChildMenus(String parentId);

    /**
     * Retrieve top-level menu ID by menu ID
     *
     * @param menuId menu ID
     * @return {@link String}
     * @author Fan
     * @since 2024/3/14 11:42
     */
    String getTopMenuId(String menuId);

    /**
     * Add menu
     *
     * @param menuDO {@link MenuDO}
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/14 11:43
     */
    Integer addMenu(MenuDO menuDO);

    /**
     * Update menu
     *
     * @param menuDO {@link MenuDO}
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/14 11:43
     */
    Integer updateMenu(MenuDO menuDO);

    /**
     * Batch delete menus via ID list
     *
     * @param ids ID list
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/14 11:43
     */
    Integer batchDeleteMenus(Set<String> ids);
}