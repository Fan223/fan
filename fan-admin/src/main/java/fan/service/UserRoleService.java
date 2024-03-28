package fan.service;

import java.util.Set;

/**
 * User role association interface
 *
 * @author Fan
 * @since 2024/3/20 17:12
 */
public interface UserRoleService {

    Set<String> listRoleIds(String userId);
}