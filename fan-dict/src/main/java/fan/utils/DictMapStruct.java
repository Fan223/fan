package fan.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.pojo.dto.WordDTO;
import fan.pojo.entity.WordDO;
import fan.pojo.vo.WordVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Dictionary conversion class
 *
 * @author Fan
 * @since 2024/3/27 11:09
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapStruct {

    /**
     * Convert {@link WordDO} to {@link WordVO}
     *
     * @param wordDO {@link WordDO}
     * @return {@link WordVO}
     * @author Fan
     * @since 2024/3/28 16:39
     */
    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updateTime", source = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    WordVO transWord(WordDO wordDO);

    /**
     * Convert {@link WordDO} list to {@link WordVO} list
     *
     * @param wordDos {@link WordDO} list
     * @return {@link List<WordVO>}
     * @author Fan
     * @since 2024/3/28 16:41
     */
    List<WordVO> transWord(List<WordDO> wordDos);

    /**
     * Paginate {@link WordDO} to {@link WordVO}
     *
     * @param wordPage Paginate {@link WordDO}
     * @return {@link Page<WordVO>}
     * @author Fan
     * @since 2024/3/28 16:41
     */
    Page<WordVO> transWord(Page<WordDO> wordPage);

    /**
     * Convert {@link WordDTO} to {@link WordDO}
     *
     * @param wordDTO {@link WordDTO}
     * @return {@link WordDO}
     * @author Fan
     * @since 2024/3/28 16:42
     */
    WordDO transWord(WordDTO wordDTO);
}