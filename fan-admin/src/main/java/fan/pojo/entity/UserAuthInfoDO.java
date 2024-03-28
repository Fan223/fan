package fan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * User authorization information entity class
 *
 * @author Fan
 * @since 2024/3/20 11:04
 */
@Data
@TableName("user_auth_info")
public class UserAuthInfoDO {

    @TableId
    private String id;

    private String uid;

    private String identityType;

    private String identifier;

    private String credential;

    private String flag;

    private Timestamp createTime;

    private Timestamp updateTime;
}