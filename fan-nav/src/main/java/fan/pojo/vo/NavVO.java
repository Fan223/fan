package fan.pojo.vo;

import lombok.Data;

/**
 * Resource navigation value object
 *
 * @author Fan
 * @since 2024/3/27 11:01
 */
@Data
public class NavVO {

    private String id;

    private String name;

    private String link;

    private String icon;

    private String type;

    private String createTime;

    private String updateTime;
}