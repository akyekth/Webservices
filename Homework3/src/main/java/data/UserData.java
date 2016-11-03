package data;


import java.util.HashMap;

public class UserData {
    private static HashMap<String, String> data = new HashMap<String, String>();

    static {
        data.put("user1", "user1");
        data.put("user2", "user2");
        data.put("user3", "user3");
        data.put("user4", "user4");
    }

    public static boolean registeredUser(String username, String password) {
        return data.containsKey(username) && data.get(username).equals(password);
    }
}
