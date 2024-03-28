package fan.pojo.dto;

import lombok.Data;

/**
 * Resource navigation data transfer object
 *
 * @author Fan
 * @since 2024/3/27 11:47
 */
@Data
public class NavDTO {

    private String id;

    private String name;

    private String link;

    private String icon;

    private String type;
}