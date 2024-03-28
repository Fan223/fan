package fan.consts;

/**
 * Auth Constant Class
 *
 * @author Fan
 * @since 2024/3/20 11:29
 */
public class AuthConst {

    private AuthConst() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    private static final String[] AUTH_WHITELIST = {"/api/**", "/login"};

    public static String[] getAuthWhitelist() {
        return AUTH_WHITELIST;
    }
}