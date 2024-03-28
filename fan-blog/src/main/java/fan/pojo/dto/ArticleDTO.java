package fan.pojo.dto;

import lombok.Data;

/**
 * Article data transfer object
 *
 * @author Fan
 * @since 2024/2/22 14:19
 */
@Data
public class ArticleDTO {

    private String id;

    private String categoryId;

    private String title;

    private String content;

    private String cover;

    private String flag;
}