package fan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Category entity class
 *
 * @author Fan
 * @since 2024/2/22 17:23
 */
@Data
@TableName("category")
public class CategoryDO {

    @TableId
    private String id;

    private String name;

    private Integer orderNum;

    private String flag;

    private Timestamp createTime;

    private Timestamp updateTime;
}