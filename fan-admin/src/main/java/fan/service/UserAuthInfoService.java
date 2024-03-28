package fan.service;

import fan.pojo.entity.UserAuthInfoDO;

/**
 * User authorization information interface
 *
 * @author Fan
 * @since 2024/3/20 11:10
 */
public interface UserAuthInfoService {

    /**
     * Get user authorization information
     *
     * @param identifier Identity Identifier
     * @return {@link UserAuthInfoDO}
     * @author Fan
     * @since 2024/3/26 11:52
     */
    UserAuthInfoDO getUserAuthInfo(String identifier);

    /**
     * Add user authorization information
     *
     * @param userAuthInfoDO {@link UserAuthInfoDO}
     * @author Fan
     * @since 2024/3/26 11:52
     */
    void addUserAuthInfo(UserAuthInfoDO userAuthInfoDO);
}