package fan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fan.dao.RoleMenuDAO;
import fan.pojo.entity.RoleMenuDO;
import fan.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Role menu association interface implementation class
 *
 * @author Fan
 * @since 2024/3/20 17:13
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    private final RoleMenuDAO roleMenuDAO;

    @Autowired
    public RoleMenuServiceImpl(RoleMenuDAO roleMenuDAO) {
        this.roleMenuDAO = roleMenuDAO;
    }

    @Override
    public Set<String> listMenuIds(String roleId) {
        List<RoleMenuDO> roleMenuDos = roleMenuDAO.selectList(new LambdaQueryWrapper<RoleMenuDO>().eq(RoleMenuDO::getRid, roleId));
        return roleMenuDos.stream().map(RoleMenuDO::getMid).collect(Collectors.toSet());
    }
}