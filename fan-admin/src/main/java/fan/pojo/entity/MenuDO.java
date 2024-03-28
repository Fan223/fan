package fan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Menu entity class
 *
 * @author Fan
 * @since 2024/2/23 9:29
 */
@Data
@TableName("menu")
public class MenuDO {

    @TableId
    private String id;

    private String parentId;

    private String position;

    private String name;

    private String path;

    private String authority;

    private String component;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private String flag;

    private Timestamp createTime;

    private Timestamp updateTime;
}