package fan.service;

import fan.pojo.entity.RoleDO;

/**
 * Role interface
 *
 * @author Fan
 * @since 2024/3/20 17:10
 */
public interface RoleService {

    RoleDO getRole(String roleId);
}