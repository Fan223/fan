package fan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fan.pojo.entity.MenuDO;

import java.util.List;

/**
 * Menu data access object
 *
 * @author Fan
 * @since 2024/2/23 9:36
 */
public interface MenuDAO extends BaseMapper<MenuDO> {

    List<MenuDO> listChildMenus(String parentId);

    String getTopMenuId(String menuId);
}