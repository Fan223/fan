package fan.pojo.query;

import fan.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Resource navigation query parameters
 *
 * @author Fan
 * @since 2024/3/27 11:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NavQuery extends PageQuery {

    private String name;

    private String type;
}