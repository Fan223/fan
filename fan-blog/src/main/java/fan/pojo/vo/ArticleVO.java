package fan.pojo.vo;

import lombok.Data;

/**
 * Article value object
 *
 * @author Fan
 * @since 2024/2/22 14:18
 */
@Data
public class ArticleVO {

    private String id;

    private String categoryId;

    private String title;

    private String content;

    private String cover;

    private Integer view;

    private Integer state;

    private String flag;

    private String createTime;

    private String updateTime;
}