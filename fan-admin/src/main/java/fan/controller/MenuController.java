package fan.controller;

import fan.core.Response;
import fan.core.collection.CollectionUtil;
import fan.core.collection.ListUtil;
import fan.pojo.dto.MenuDTO;
import fan.pojo.entity.MenuDO;
import fan.pojo.query.MenuQuery;
import fan.pojo.vo.MenuVO;
import fan.service.MenuService;
import fan.utils.AdminMapStruct;
import fan.utils.AdminUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Menu controller
 *
 * @author Fan
 * @since 2024/2/23 9:25
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    private final MenuService menuService;
    private final AdminMapStruct adminMapStruct;

    @Autowired
    public MenuController(MenuService menuService, AdminMapStruct adminMapStruct) {
        this.menuService = menuService;
        this.adminMapStruct = adminMapStruct;
    }

    @GetMapping("/listNavMenus")
    public Response<List<MenuVO>> listNavMenus(Principal principal) {
        String userId = principal.getName();

        if ("00000001".equals(userId)) {
            return listMenus(MenuQuery.builder().flag("Y").position("top").build());
        } else {
            List<MenuDO> menuDos = menuService.listNavMenus(userId);
            return Response.success(AdminUtil.buildTree(adminMapStruct.transMenu(menuDos)));
        }
    }

    @GetMapping("/listRouteMenus")
    public Response<List<MenuVO>> listRouteMenus(Principal principal) {
        String userId = principal.getName();

        if ("00000001".equals(userId)) {
            return listMenus(MenuQuery.builder().flag("Y").type(ListUtil.list("1", "2", "4")).build());
        } else {
            List<MenuDO> menuDos = menuService.listRouteMenus(userId);
            return Response.success(AdminUtil.buildTree(adminMapStruct.transMenu(menuDos)));
        }
    }

    @GetMapping("/listMenus")
    public Response<List<MenuVO>> listMenus(MenuQuery menuQuery) {
        List<MenuDO> menuDos = menuService.listMenus(menuQuery);
        return Response.success(AdminUtil.buildTree(adminMapStruct.transMenu(menuDos)));
    }

    @GetMapping("/listChildMenus/{parentId}")
    public Response<List<MenuVO>> listChildMenus(@PathVariable("parentId") String parentId) {
        List<MenuDO> menuDos = menuService.listChildMenus(parentId);
        return Response.success(AdminUtil.buildTree(adminMapStruct.transMenu(menuDos)));
    }

    @GetMapping("/getTopMenuId/{menuId}")
    public Response<String> getTopMenuId(@PathVariable("menuId") String menuId) {
        return Response.success(menuService.getTopMenuId(menuId));
    }

    @PostMapping("/addMenu")
    public Response<Integer> addMenu(@RequestBody MenuDTO menuDTO) {
        MenuDO menuDO = adminMapStruct.transMenu(menuDTO);
        return Response.success(menuService.addMenu(menuDO));
    }

    @PutMapping("/updateMenu")
    public Response<Integer> updateMenu(@RequestBody MenuDTO menuDTO) {
        MenuDO menuDO = adminMapStruct.transMenu(menuDTO);
        return Response.success(menuService.updateMenu(menuDO));
    }

    @DeleteMapping("/batchDeleteMenus/{id}")
    public Response<Integer> batchDeleteMenus(@PathVariable("id") Set<String> ids) {
        for (String id : ids) {
            List<MenuDO> menuDos = menuService.listChildMenus(id);
            if (CollectionUtil.isNotEmpty(menuDos)
                    && !new HashSet<>(ids).containsAll(menuDos.stream().map(MenuDO::getId).toList())) {
                return Response.fail("请先删除子菜单", null);
            }
        }
        return Response.success(menuService.batchDeleteMenus(ids));
    }
}