package fan.utils;

import fan.pojo.dto.MenuDTO;
import fan.pojo.dto.UserDTO;
import fan.pojo.entity.MenuDO;
import fan.pojo.entity.UserAuthInfoDO;
import fan.pojo.entity.UserInfoDO;
import fan.pojo.vo.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Admin conversion class
 *
 * @author Fan
 * @since 2024/2/23 9:41
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminMapStruct {

    /**
     * Convert {@link MenuDO} to {@link MenuVO}
     *
     * @param menuDO {@link MenuDO}
     * @return {@link MenuVO}
     * @author Fan
     * @since 2024/2/23 9:42
     */
    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updateTime", source = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    MenuVO transMenu(MenuDO menuDO);

    /**
     * Convert {@link MenuDO} list to {@link MenuVO} list
     *
     * @param menuDos {@link MenuDO} list
     * @return {@link List<MenuVO>}
     * @author Fan
     * @since 2024/2/23 9:43
     */
    List<MenuVO> transMenu(List<MenuDO> menuDos);

    /**
     * Convert {@link MenuDTO} to {@link MenuDO}
     *
     * @param menuDTO {@link MenuDTO}
     * @return {@link MenuDO}
     * @author Fan
     * @since 2024/2/23 9:43
     */
    MenuDO transMenu(MenuDTO menuDTO);

    /**
     * Convert {@link UserDTO} to {@link UserInfoDO}
     *
     * @param userDTO {@link UserDTO}
     * @return {@link UserInfoDO}
     * @author Fan
     * @since 2024/3/26 11:56
     */
    UserInfoDO transUserInfo(UserDTO userDTO);

    /**
     * Convert {@link UserDTO} to {@link UserAuthInfoDO}
     *
     * @param userDTO {@link UserDTO}
     * @return {@link UserAuthInfoDO}
     * @author Fan
     * @since 2024/3/26 11:56
     */
    UserAuthInfoDO transUserAuthInfo(UserDTO userDTO);
}