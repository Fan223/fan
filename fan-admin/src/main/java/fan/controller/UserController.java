package fan.controller;

import grey.fable.base.net.Response;
import grey.fable.base.util.IdUtils;
import fan.pojo.dto.UserDTO;
import fan.pojo.entity.UserAuthInfoDO;
import fan.pojo.entity.UserInfoDO;
import fan.service.UserAuthInfoService;
import fan.service.UserInfoService;
import fan.utils.AdminMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * User controller
 *
 * @author Fan
 * @since 2024/3/25 10:09
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    private final UserInfoService userInfoService;
    private final UserAuthInfoService userAuthInfoService;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapStruct adminMapStruct;

    @Autowired
    public UserController(UserInfoService userInfoService, UserAuthInfoService userAuthInfoService, PasswordEncoder passwordEncoder, AdminMapStruct adminMapStruct) {
        this.userInfoService = userInfoService;
        this.userAuthInfoService = userAuthInfoService;
        this.passwordEncoder = passwordEncoder;
        this.adminMapStruct = adminMapStruct;
    }

    @PostMapping("/addUser")
    @Transactional
    public Response<Integer> addUser(@RequestBody UserDTO userDTO) {
        UserAuthInfoDO userAuthInfo = userAuthInfoService.getUserAuthInfo(userDTO.getIdentifier());

        if (null != userAuthInfo) {
            return Response.fail("User already exists", 0);
        }

        String userId = IdUtils.getSnowflakeNextIdStr();
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        UserInfoDO userInfoDO = adminMapStruct.transUserInfo(userDTO);
        userInfoDO.setId(userId);
        userInfoDO.setCreateTime(now);
        userInfoDO.setUpdateTime(now);
        userInfoService.addUserInfo(userInfoDO);

        UserAuthInfoDO userAuthInfoDO = adminMapStruct.transUserAuthInfo(userDTO);
        userAuthInfoDO.setUid(userId);
        userAuthInfoDO.setCredential(passwordEncoder.encode(userDTO.getCredential()));
        userAuthInfoDO.setCreateTime(now);
        userAuthInfoDO.setUpdateTime(now);
        userAuthInfoService.addUserAuthInfo(userAuthInfoDO);

        return Response.success("添加用户成功", 1);
    }
}