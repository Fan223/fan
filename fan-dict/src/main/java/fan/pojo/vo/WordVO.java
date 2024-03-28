package fan.pojo.vo;

import lombok.Data;

/**
 * Word value object
 *
 * @author Fan
 * @since 2024/3/27 16:33
 */
@Data
public class WordVO {

    private String id;

    private String en;

    private String phonetic;

    private String cn;

    private String type;

    private String createTime;

    private String updateTime;
}