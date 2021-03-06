package ca.uottawa.service;

import ca.uottawa.utils.Result;

public interface UserService {

    Result login(String username, String password);
}
