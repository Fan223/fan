package fan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import grey.fable.base.collection.CollectionUtils;
import grey.fable.base.text.StringUtils;
import grey.fable.base.util.IdUtils;
import fan.dao.MenuDAO;
import fan.pojo.entity.MenuDO;
import fan.pojo.query.MenuQuery;
import fan.service.MenuService;
import fan.service.RoleMenuService;
import fan.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Menu interface implementation class
 *
 * @author Fan
 * @since 2024/2/23 9:38
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuDAO menuDAO;
    private final UserRoleService userRoleService;
    private final RoleMenuService roleMenuService;

    @Autowired
    public MenuServiceImpl(MenuDAO menuDAO, UserRoleService userRoleService, RoleMenuService roleMenuService) {
        this.menuDAO = menuDAO;
        this.userRoleService = userRoleService;
        this.roleMenuService = roleMenuService;
    }

    @Override
    public List<MenuDO> listNavMenus(String userId) {
        Set<String> ids = listMenuIds(userId);
        return listMenus(MenuQuery.builder().position("top").flag("Y").ids(ids).build());
    }

    private Set<String> listMenuIds(String userId) {
        Set<String> roleIds = userRoleService.listRoleIds(userId);

        Set<String> ids = new HashSet<>();
        for (String roleId : roleIds) {
            Set<String> menuIds = roleMenuService.listMenuIds(roleId);
            ids.addAll(menuIds);
        }
        return ids;
    }

    @Override
    public List<MenuDO> listRouteMenus(String userId) {
        Set<String> ids = listMenuIds(userId);
        return listMenus(MenuQuery.builder().flag("Y").ids(ids).build());
    }

    @Override
    public List<MenuDO> listMenus(MenuQuery menuQuery) {
        LambdaQueryWrapper<MenuDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(menuQuery.getPosition()), MenuDO::getPosition, menuQuery.getPosition())
                .eq(StringUtils.isNotBlank(menuQuery.getFlag()), MenuDO::getFlag, menuQuery.getFlag())
                .like(StringUtils.isNotBlank(menuQuery.getName()), MenuDO::getName, menuQuery.getName())
                .in(CollectionUtils.isNotEmpty(menuQuery.getIds()), MenuDO::getId, menuQuery.getIds())
                .in(CollectionUtils.isNotEmpty(menuQuery.getType()), MenuDO::getType, menuQuery.getType())
                .orderByAsc(MenuDO::getOrderNum);
        return menuDAO.selectList(queryWrapper);
    }

    @Override
    public List<MenuDO> listChildMenus(String parentId) {
        return menuDAO.listChildMenus(parentId);
    }

    @Override
    public String getTopMenuId(String menuId) {
        return menuDAO.getTopMenuId(menuId);
    }

    @Override
    public Integer addMenu(MenuDO menuDO) {
        menuDO.setId(String.valueOf(IdUtils.getSnowflakeId()));

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        menuDO.setCreateTime(now);
        menuDO.setUpdateTime(now);
        return menuDAO.insert(menuDO);
    }

    @Override
    public Integer updateMenu(MenuDO menuDO) {
        menuDO.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        return menuDAO.updateById(menuDO);
    }

    @Override
    public Integer batchDeleteMenus(Set<String> ids) {
        return menuDAO.deleteByIds(ids);
    }
}