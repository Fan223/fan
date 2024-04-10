package fan.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.pojo.entity.ArticleDO;
import fan.pojo.query.ArticleQuery;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Article interface
 *
 * @author Fan
 * @since 2024/2/22 14:40
 */
public interface ArticleService {

    /**
     * Clean invalid images
     *
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/14 11:08
     */
    Integer cleanInvalidImages();

    /**
     * Export all articles
     *
     * @return {@link File}
     * @author Fan
     * @since 2024/3/14 11:11
     */
    File exportAllArticles();

    /**
     * Batch export articles via ID list
     *
     * @param ids ID list
     * @return {@link File}
     * @author Fan
     * @since 2024/3/14 11:12
     */
    File batchExportArticles(List<String> ids);

    /**
     * Paginate articles retrieval
     *
     * @param articleQuery {@link ArticleQuery}
     * @return {@link Page<ArticleDO>}
     * @author Fan
     * @since 2024/3/14 11:13
     */
    Page<ArticleDO> pageArticles(ArticleQuery articleQuery);

    /**
     * Retrieve article list via ID list
     *
     * @param ids ID list
     * @return {@link List<ArticleDO>}
     * @author Fan
     * @since 2024/3/14 11:15
     */
    List<ArticleDO> listArticles(List<String> ids);

    /**
     * Retrieve article by ID
     *
     * @param id ID
     * @return {@link ArticleDO}
     * @author Fan
     * @since 2024/3/14 11:16
     */
    ArticleDO getArticleById(String id);

    /**
     * Retrieve a list of article counts for each category
     *
     * @return {@link Map}
     * @author Fan
     * @since 2024/3/14 11:16
     */
    Map<String, String> listCountsByCategories();

    /**
     * Save article
     *
     * @param articleDO {@link ArticleDO}
     * @return {@link String}
     * @author Fan
     * @since 2024/3/14 11:17
     */
    ArticleDO saveArticle(ArticleDO articleDO);

    /**
     * Update article
     *
     * @param articleDO {@link ArticleDO}
     * @return {@link String}
     * @author Fan
     * @since 2024/3/14 11:17
     */
    ArticleDO updateArticle(ArticleDO articleDO);

    /**
     * Batch delete articles via ID list
     *
     * @param ids ID list
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/14 11:17
     */
    Integer batchDeleteArticles(List<String> ids);
}