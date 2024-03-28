package fan.pojo.query;

import fan.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Article query parameters
 *
 * @author Fan
 * @since 2024/2/22 14:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleQuery extends PageQuery {

    private String categoryId;

    private String title;
}