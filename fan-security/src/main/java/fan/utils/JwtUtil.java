package fan.utils;

import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;

import java.util.Date;

/**
 * Jwt utility class
 *
 * @author Fan
 * @since 2024/3/20 11:25
 */
public class JwtUtil {

    private static final RSA RSA = new RSA();

    private static final int EXPIRE = 1000 * 60 * 60 * 24 * 3;

    private JwtUtil() {
        throw new UnsupportedOperationException("Utility classes can't be instantiated.");
    }

    public static String generate(String username) {
        return JWT.create()
                .setSubject(username)
                .setExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .setSigner("RS256", RSA.getPrivateKey())
                .sign();
    }

    public static JWT parse(String token) {
        try {
            JWTValidator.of(token).validateAlgorithm(JWTSignerUtil.rs256(RSA.getPublicKey())).validateDate(new Date());
        } catch (Exception e) {
            return null;
        }

        return JWT.of(token);
    }
}