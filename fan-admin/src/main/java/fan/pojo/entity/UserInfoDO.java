package fan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * User information entity class
 *
 * @author Fan
 * @since 2024/3/20 17:03
 */
@Data
@TableName("user_info")
public class UserInfoDO {

    @TableId
    private String id;

    private String nickname;

    private String avatar;

    private String flag;

    private Timestamp createTime;

    private Timestamp updateTime;
}