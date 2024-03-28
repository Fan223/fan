package fan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Resource navigation entity class
 *
 * @author Fan
 * @since 2024/3/27 10:57
 */
@Data
@TableName("nav")
public class NavDO {

    @TableId
    private String id;

    private String name;

    private String link;

    private String icon;

    private String type;

    private Timestamp createTime;

    private Timestamp updateTime;
}