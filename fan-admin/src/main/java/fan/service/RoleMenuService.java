package fan.service;

import java.util.Set;

/**
 * Role menu association interface
 *
 * @author Fan
 * @since 2024/3/20 17:13
 */
public interface RoleMenuService {

    Set<String> listMenuIds(String roleId);
}