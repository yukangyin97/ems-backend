package ca.uottawa.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtil {

    private static final String secret = "@falj123#1flakj--5k";

    /**
     * create token header.payload.signature
     */
    public static String getToken(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);  // expired date equals to 7 days

        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v) -> {
            builder.withClaim(k, v);
        });

        // sign
        return builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(secret));
    }

    /**
     * verify token
     */
    public static DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }
}
