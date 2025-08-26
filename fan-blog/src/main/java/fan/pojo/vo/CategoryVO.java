package fan.pojo.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Category value object
 *
 * @author Fan
 * @since 2024/2/22 17:25
 */
@Data
public class CategoryVO {

    private String id;

    private String parentId;

    private String name;

    private Integer orderNum;

    private String flag;

    private String createTime;

    private String updateTime;

    private String count;

    private List<CategoryVO> children;

    public List<CategoryVO> getChildren() {
        if (null == children) {
            children = new ArrayList<>();
        }
        return children;
    }
}