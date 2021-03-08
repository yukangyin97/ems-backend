package ca.uottawa.service;

import ca.uottawa.repository.UserRepository;
import ca.uottawa.utils.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void loginTest() {
        /**
         * Precondition: executing db.init script to insert two user records to user table
         */

        // Scenario 1: Correct credentials
        String username = "admin1";
        String password = "123456";
        Result result = userService.login(username, password);
        Assertions.assertEquals(200, result.getCode());

        // Scenario 2: Wrong credentials
        username = "admin1";
        password = "12345";
        result = userService.login(username, password);
        Assertions.assertEquals(400, result.getCode());
    }
}
