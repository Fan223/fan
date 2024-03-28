package fan.service.impl;

import fan.dao.UserInfoDAO;
import fan.pojo.entity.UserInfoDO;
import fan.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User information interface implementation class
 *
 * @author Fan
 * @since 2024/3/20 17:09
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoDAO userInfoDAO;

    @Autowired
    public UserInfoServiceImpl(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    @Override
    public UserInfoDO getUserinfo(String userId) {
        return userInfoDAO.selectById(userId);
    }

    @Override
    public void addUserInfo(UserInfoDO userInfoDO) {
        userInfoDAO.insert(userInfoDO);
    }
}