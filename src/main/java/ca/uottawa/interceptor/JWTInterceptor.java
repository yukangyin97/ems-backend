package ca.uottawa.interceptor;

import ca.uottawa.utils.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        String token = request.getHeader("auth-token");  // get jwt token from request header

        try {
            JWTUtil.verifyToken(token);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        Map<String, String> map = new HashMap<>();
        map.put("error", "Please login first");
        String json = new ObjectMapper().writeValueAsString(map);
        response.setStatus(401);  // unauthorized
        response.setContentType("application/json;character=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
