package fan.filter;

import cn.hutool.jwt.JWT;
import fan.consts.AuthConst;
import fan.core.text.StringUtil;
import fan.pojo.entity.MenuDO;
import fan.redis.RedisUtil;
import fan.service.MenuService;
import fan.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jwt Authentication Filter
 *
 * @author Fan
 * @since 2024/3/20 11:16
 */
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final MenuService menuService;

    @Autowired
    public JwtAuthenticationFilter(MenuService menuService) {
        this.menuService = menuService;
    }

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Allowed whitelist
        String uri = request.getRequestURI();
        for (String path : AuthConst.getAuthWhitelist()) {
            if (antPathMatcher.match(path, uri)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtil.isBlank(token)) {
            token = request.getParameter("token");
        }

        // Validation failed
        JWT jwt = JwtUtil.parse(token);
        if (null == jwt) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String userId = jwt.getPayload("sub").toString();
        Object authorities = RedisUtil.hashGet(userId, "authorities");
        if (null == authorities) {
            List<MenuDO> menuDos = menuService.listRouteMenus(userId);
            authorities = menuDos.stream().map(MenuDO::getAuthority).collect(Collectors.joining(","));
            RedisUtil.hashSet(userId, "authorities", authorities);
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities.toString());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken
                .authenticated(userId, "", grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}