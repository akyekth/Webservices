package resource;


import services.AuthenticateService;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService(endpointInterface = "resource.AuthServiceResource")
//@HandlerChain(file = "src/main/resources/soaphandler.xml")
public class AuthServiceResourceImpl implements AuthServiceResource {
    public String login(String username, String password) {
        return AuthenticateService.getInstance().login(username, password);
    }
}
