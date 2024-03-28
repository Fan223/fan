package fan.pojo.dto;

import lombok.Data;

/**
 * User data transfer object
 *
 * @author Fan
 * @since 2024/3/25 10:18
 */
@Data
public class UserDTO {

    private String identityType;

    private String identifier;

    private String credential;

    private String nickname;

    private String avatar;

    private String flag;
}