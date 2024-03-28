package fan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fan.dao.UserRoleDAO;
import fan.pojo.entity.UserRoleDO;
import fan.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User role association interface implementation class
 *
 * @author Fan
 * @since 2024/3/20 17:12
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleDAO userRoleDAO;

    @Autowired
    public UserRoleServiceImpl(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Override
    public Set<String> listRoleIds(String userId) {
        List<UserRoleDO> userRoleDOS = userRoleDAO.selectList(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getUid, userId));
        return userRoleDOS.stream().map(UserRoleDO::getRid).collect(Collectors.toSet());
    }
}