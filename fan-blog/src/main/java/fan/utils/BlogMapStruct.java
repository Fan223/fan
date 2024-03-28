package fan.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.pojo.dto.ArticleDTO;
import fan.pojo.entity.ArticleDO;
import fan.pojo.entity.CategoryDO;
import fan.pojo.vo.ArticleVO;
import fan.pojo.vo.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Blog conversion class
 *
 * @author Fan
 * @since 2024/2/22 16:39
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BlogMapStruct {

    /**
     * Convert {@link ArticleDO} to {@link ArticleVO}
     *
     * @param articleDO {@link ArticleDO}
     * @return {@link ArticleVO}
     * @author Fan
     * @since 2024/2/22 16:41
     */
    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updateTime", source = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ArticleVO transArticles(ArticleDO articleDO);

    /**
     * Convert {@link ArticleDO} list to {@link ArticleVO} list
     *
     * @param articleDos {@link ArticleDO} list
     * @return {@link List<ArticleVO>}
     * @author Fan
     * @since 2024/2/22 16:42
     */
    List<ArticleVO> transArticles(List<ArticleDO> articleDos);

    /**
     * Paginate {@link ArticleDO} to {@link ArticleVO}
     *
     * @param doPage Paginate {@link ArticleDO}
     * @return {@link Page<ArticleVO>}
     * @author Fan
     * @since 2024/2/22 16:43
     */
    Page<ArticleVO> transArticles(Page<ArticleDO> doPage);

    /**
     * Convert {@link ArticleDTO} to {@link ArticleDO}
     *
     * @param articleDTO {@link ArticleDTO}
     * @return {@link ArticleDO}
     * @author Fan
     * @since 2024/2/27 13:53
     */
    ArticleDO transArticles(ArticleDTO articleDTO);

    /**
     * Convert {@link CategoryDO} to {@link CategoryVO}
     *
     * @param categoryDO {@link CategoryDO}
     * @return {@link CategoryVO}
     * @author Fan
     * @since 2024/2/23 4:21
     */
    CategoryVO transCategories(CategoryDO categoryDO);

    /**
     * Convert {@link CategoryDO} list to {@link CategoryVO} list
     *
     * @param categoryDos {@link CategoryDO} list
     * @return {@link List<CategoryVO>}
     * @author Fan
     * @since 2024/2/23 4:22
     */
    List<CategoryVO> transCategories(List<CategoryDO> categoryDos);
}