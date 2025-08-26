package fan.pojo.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 菜单数据传输对象.
 *
 * @author Fan
 * @since 2025/8/26 10:15
 */
@Data
public class CategoryDTO {

    private String id;

    private String parentId;

    private String name;

    private String orderNum;

    private String flag;
}
