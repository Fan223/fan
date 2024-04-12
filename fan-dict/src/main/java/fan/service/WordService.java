package fan.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.pojo.entity.WordDO;
import fan.pojo.query.WordQuery;

import java.util.List;

/**
 * Word interface
 *
 * @author Fan
 * @since 2024/3/27 16:22
 */
public interface WordService {

    /**
     * Paginate words retrieval
     *
     * @param wordQuery {@link WordQuery}
     * @return {@link Page<WordDO>}
     * @author Fan
     * @since 2024/3/28 16:43
     */
    Page<WordDO> pageWords(WordQuery wordQuery);

    /**
     * Retrieve words via ID list
     *
     * @param wordQuery {@link WordQuery}
     * @return {@link List<WordDO>}
     * @author Fan
     * @since 2024/4/11 16:12
     */
    List<WordDO> listWords(WordQuery wordQuery);

    /**
     * Add word
     *
     * @param wordDO {@link WordDO}
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/28 16:43
     */
    Integer addWord(WordDO wordDO);

    /**
     * Update word
     *
     * @param wordDO {@link WordDO}
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/28 16:44
     */
    Integer updateWord(WordDO wordDO);

    /**
     * Batch delete words via ID list
     *
     * @param ids ID list
     * @return {@link Integer}
     * @author Fan
     * @since 2024/3/28 16:44
     */
    Integer batchDeleteWords(List<String> ids);
}