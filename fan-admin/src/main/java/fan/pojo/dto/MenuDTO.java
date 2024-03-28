package fan.pojo.dto;

import lombok.Data;

/**
 * Menu data transfer object
 *
 * @author Fan
 * @since 2024/2/23 9:34
 */
@Data
public class MenuDTO {

    private String id;

    private String parentId;

    private String position;

    private String name;

    private String path;

    private String authority;

    private String component;

    private String type;

    private String icon;

    private String orderNum;

    private String flag;
}