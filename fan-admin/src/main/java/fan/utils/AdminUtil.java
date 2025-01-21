package fan.utils;

import grey.fable.base.collection.CollectionUtils;
import grey.fable.base.collection.ListUtils;
import fan.pojo.vo.MenuVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Admin utility class
 *
 * @author Fan
 * @since 2024/2/23 9:45
 */
public class AdminUtil {

    private AdminUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static List<MenuVO> buildTree(List<MenuVO> menuVos) {
        if (CollectionUtils.isEmpty(menuVos)) {
            return new ArrayList<>();
        }
        List<MenuVO> menusTree = new ArrayList<>(menuVos.size());

        // Convert the menu list into a Map, with the Key being the ID.
        Map<String, MenuVO> menuVoMap = menuVos.stream().collect(Collectors.toMap(MenuVO::getId, menuVO -> menuVO));

        // Loop through the menu list
        for (MenuVO menuVO : menuVos) {
            // Determine if the current menu has a parent menu
            MenuVO parentMenuVO = menuVoMap.get(menuVO.getParentId());

            // If the parent menu is empty, the current menu is a top-level menu, directly added to the result list.
            if (null == parentMenuVO) {
                menusTree.add(menuVO);
                continue;
            }

            // If it is not empty, add the current menu to the submenus of the obtained parent menu.
            parentMenuVO.getChildren().add(menuVO);
        }

        return menusTree;
    }
}