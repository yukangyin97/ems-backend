package ca.uottawa.controller;

import ca.uottawa.entity.User;
import ca.uottawa.service.UserService;
import ca.uottawa.utils.JWTUtil;
import ca.uottawa.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password) {
        logger.info("username [{}], password [{}]", username, password);

        Result result = userService.login(username, password);
        Map<String, String> map = new HashMap<>();

        int code = result.getCode();
        if (code == 200) {
            User user = (User) result.getData();
            map.put("username", user.getUsername());

            // generate jwt token
            String token = JWTUtil.getToken(map);
            logger.info("token [{}]", token);
            map.clear();  // clear username
            map.put("token", token);
        } else {  // code == 400
            logger.error("Error message: {}", result.getMsg());
            map.put("error", result.getMsg());
        }
        return ResponseEntity.status(code).body(map);
    }
}
