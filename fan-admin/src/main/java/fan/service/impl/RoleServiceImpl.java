package fan.service.impl;

import fan.dao.RoleDAO;
import fan.pojo.entity.RoleDO;
import fan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Role interface implementation class
 *
 * @author Fan
 * @since 2024/3/20 17:11
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public RoleDO getRole(String roleId) {
        return roleDAO.selectById(roleId);
    }
}