package fan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Word entity class
 *
 * @author Fan
 * @since 2024/3/27 16:25
 */
@Data
@TableName("word")
public class WordDO {

    @TableId
    private String id;

    private String en;

    private String phonetic;

    private String cn;

    private String type;

    private Timestamp createTime;

    private Timestamp updateTime;
}