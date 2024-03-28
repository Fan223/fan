package fan.service;

import fan.pojo.entity.UserInfoDO;

/**
 * User information interface
 *
 * @author Fan
 * @since 2024/3/20 17:09
 */
public interface UserInfoService {

    /**
     * Get user information
     *
     * @param userId User ID
     * @return {@link UserInfoDO}
     * @author Fan
     * @since 2024/3/26 11:54
     */
    UserInfoDO getUserinfo(String userId);

    /**
     * Add user information
     *
     * @param userInfoDO {@link UserInfoDO}
     * @author Fan
     * @since 2024/3/26 11:54
     */
    void addUserInfo(UserInfoDO userInfoDO);
}