package fan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fan.dao.UserAuthInfoDAO;
import fan.pojo.entity.UserAuthInfoDO;
import fan.service.UserAuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User authorization information interface implementation class
 *
 * @author Fan
 * @since 2024/3/20 11:10
 */
@Service
public class UserAuthInfoServiceImpl implements UserAuthInfoService {

    private final UserAuthInfoDAO userAuthInfoDAO;

    @Autowired
    public UserAuthInfoServiceImpl(UserAuthInfoDAO userAuthInfoDAO) {
        this.userAuthInfoDAO = userAuthInfoDAO;
    }

    @Override
    public UserAuthInfoDO getUserAuthInfo(String identifier) {
        LambdaQueryWrapper<UserAuthInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAuthInfoDO::getIdentifier, identifier);
        return userAuthInfoDAO.selectOne(queryWrapper);
    }

    @Override
    public void addUserAuthInfo(UserAuthInfoDO userAuthInfoDO) {
        userAuthInfoDAO.insert(userAuthInfoDO);
    }
}