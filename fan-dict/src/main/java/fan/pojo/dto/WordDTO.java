package fan.pojo.dto;

import lombok.Data;

/**
 * Word data transfer object
 *
 * @author Fan
 * @since 2024/3/27 16:52
 */
@Data
public class WordDTO {

    private String id;

    private String en;

    private String phonetic;

    private String cn;

    private String type;
}