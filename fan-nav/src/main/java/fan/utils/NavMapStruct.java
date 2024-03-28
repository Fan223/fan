package fan.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.pojo.dto.NavDTO;
import fan.pojo.entity.NavDO;
import fan.pojo.vo.NavVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Resource navigation conversion class
 *
 * @author Fan
 * @since 2024/3/27 11:09
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NavMapStruct {

    /**
     * Convert {@link NavDO} to {@link NavVO}
     *
     * @param navDO {@link NavDO}
     * @return {@link NavVO}
     * @author Fan
     * @since 2024/3/28 16:29
     */
    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updateTime", source = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    NavVO transNav(NavDO navDO);

    /**
     * Convert {@link NavDO} list to {@link NavVO} list
     *
     * @param navDos {@link NavDO} list
     * @return {@link List<NavVO>}
     * @author Fan
     * @since 2024/3/28 16:29
     */
    List<NavVO> transNav(List<NavDO> navDos);

    /**
     * Paginate {@link NavDO} to {@link NavVO}
     *
     * @param navPage Paginate {@link NavDO}
     * @return {@link Page<NavVO>}
     * @author Fan
     * @since 2024/3/28 16:29
     */
    Page<NavVO> transNav(Page<NavDO> navPage);

    /**
     * Convert {@link NavDTO} to {@link NavDO}
     *
     * @param navDTO {@link NavDTO}
     * @return {@link NavDO}
     * @author Fan
     * @since 2024/3/28 16:30
     */
    NavDO transNav(NavDTO navDTO);
}