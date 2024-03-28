package fan.pojo.query;

import fan.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Word query parameters
 *
 * @author Fan
 * @since 2024/3/27 16:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WordQuery extends PageQuery {

    private String en;

    private String cn;

    private String type;
}