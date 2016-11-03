package services;


import data.UserData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class AuthenticateService {

    private static AuthenticateService instance;

    private Map<String, String> authSessions = new HashMap<String, String>();

    private AuthenticateService() {
    }

    public static AuthenticateService getInstance() {
        if(instance == null) {
            instance = new AuthenticateService();
        }
        return instance;
    }

    public String login(String username, String password) {
        String token;
        if(UserData.registeredUser(username, password)) {
            token = createToken();
            authSessions.put(token, username);
        } else {
            token = "invalid";
        }

        return token;
    }

    public boolean validToken(String token) {
        return authSessions.containsKey(token);
    }

    private String createToken() {
        return UUID.randomUUID().toString();
    }


}

