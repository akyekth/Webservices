package clients;


import resource.AuthServiceResource;
import resource.AuthServiceResourceImplService;

public class AuthClient {
    public static void main(String[] args) {
        AuthServiceResourceImplService service = new AuthServiceResourceImplService();
        AuthServiceResource authService = service.getAuthServiceResourceImplPort();

        System.out.println("Trying to login...");
        System.out.println("(FINDME) SUCCESS: " + authService.login("user1", "user1"));
        System.out.println("(FINDME) FAILURE:" + authService.login("wrong", "credentials"));
    }
}
