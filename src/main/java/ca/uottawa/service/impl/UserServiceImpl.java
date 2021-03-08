package ca.uottawa.service.impl;

import ca.uottawa.entity.User;
import ca.uottawa.repository.UserRepository;
import ca.uottawa.service.UserService;
import ca.uottawa.utils.EncryptionUtil;
import ca.uottawa.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Result login(String username, String password) {
        username = username.trim();
        password = password.trim();

        if (StringUtils.isEmpty(username)) {
            return new Result(400, "Username is required", null);
        }

        if (StringUtils.isEmpty(password)) {
            return new Result(400, "Password is required", null);
        }

        String encryptedPassword = EncryptionUtil.encrypt(password);
        Optional<User> user = userRepository.findByUsernameAndPassword(username, encryptedPassword);
        if (!user.isPresent()) {
            return new Result(400, "Wrong Credentials", null);
        }

        // user exists
        return new Result(200, null, user.get());
    }
}
