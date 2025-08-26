package fan.controller;

import fan.pojo.dto.LoginDTO;
import fan.pojo.entity.UserAuthInfoDO;
import fan.service.UserAuthInfoService;
import fan.utils.JwtUtil;
import fancy.toolkit.net.Response;
import fancy.toolkit.text.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Auth controller
 *
 * @author Fan
 * @since 2024/3/20 11:36
 */
@RestController
public class LoginController {

    private final UserAuthInfoService userAuthInfoService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserAuthInfoService userAuthInfoService, PasswordEncoder passwordEncoder) {
        this.userAuthInfoService = userAuthInfoService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        if (StringUtils.isBlank(username)) {
            return Response.fail("用户名不能为空", null);
        } else if (StringUtils.isBlank(password)) {
            return Response.fail("密码不能为空", null);
        }

        UserAuthInfoDO userAuthInfo = userAuthInfoService.getUserAuthInfo(username);
        if (null == userAuthInfo) {
            return Response.fail("用户名不存在", null);
        } else if (passwordEncoder.matches(password, userAuthInfo.getCredential())) {
            return Response.success(JwtUtil.generate(userAuthInfo.getUid()));
        }
        return Response.fail("用户名或密码错误", null);
    }
}