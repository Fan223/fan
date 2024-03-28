package fan.pojo.query;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * Menu query parameters
 *
 * @author Fan
 * @since 2024/2/23 9:35
 */
@Data
@Builder
public class MenuQuery {

    private Set<String> ids;

    private String name;

    private String position;

    private List<String> type;

    private String flag;
}