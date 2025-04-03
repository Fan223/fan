package fan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Article entity class
 *
 * @author Fan
 * @since 2024/2/22 14:17
 */
@Data
@TableName("article")
public class ArticleDO {

    @TableId
    private String id;

    private String categoryId;

    private String title;

    private String content;

    private String cover;

    private Integer view;

    private Integer state;

    private String flag;

    private Timestamp createTime;

    private Timestamp updateTime;
}