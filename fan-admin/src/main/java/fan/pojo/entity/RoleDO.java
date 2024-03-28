package fan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Role entity class
 *
 * @author Fan
 * @since 2024/3/20 17:03
 */
@Data
@TableName("role")
public class RoleDO {

    @TableId
    private String id;

    private String code;

    private String name;

    private String remark;

    private String flag;

    private Timestamp createTime;

    private Timestamp updateTime;
}