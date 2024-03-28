package fan.pojo.vo;

import lombok.Data;

/**
 * Category value object
 *
 * @author Fan
 * @since 2024/2/22 17:25
 */
@Data
public class CategoryVO {

    private String id;

    private String name;

    private Integer orderNum;

    private String flag;

    private String createTime;

    private String updateTime;

    private String count;
}