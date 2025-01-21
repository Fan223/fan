package fan.pojo.vo;

import grey.fable.base.collection.ListUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu value object
 *
 * @author Fan
 * @since 2024/2/23 9:30
 */
@Data
public class MenuVO {

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

    private String createTime;

    private String updateTime;

    private List<MenuVO> children;

    public List<MenuVO> getChildren() {
        if (null == children) {
            children = new ArrayList<>();
        }
        return children;
    }
}