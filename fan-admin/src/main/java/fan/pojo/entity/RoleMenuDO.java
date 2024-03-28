package fan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Role menu association entity class
 *
 * @author Fan
 * @since 2024/3/20 17:05
 */
@Data
@TableName("role_menu")
public class RoleMenuDO {

    @TableId
    private String id;

    private String rid;

    private String mid;

    private String flag;

    private Timestamp createTime;

    private Timestamp updateTime;
}