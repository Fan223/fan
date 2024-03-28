package fan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * User role association entity class
 *
 * @author Fan
 * @since 2024/3/20 17:04
 */
@Data
@TableName("user_role")
public class UserRoleDO {

    @TableId
    private String id;

    private String uid;

    private String rid;

    private String flag;

    private Timestamp createTime;

    private Timestamp updateTime;
}