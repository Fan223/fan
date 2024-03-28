package fan.pojo.dto;

import lombok.Data;

/**
 * Login data transfer object
 *
 * @author Fan
 * @since 2024/3/20 11:35
 */
@Data
public class LoginDTO {

    private String username;

    private String password;
}