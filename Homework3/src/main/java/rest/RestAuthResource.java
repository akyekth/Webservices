package rest;


import com.wordnik.swagger.annotations.*;
import services.AuthenticateService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

// https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X

@Path("/auth")
@Api(value = "/auth") // Marks a class as a Swagger resource.
@Produces(MediaType.APPLICATION_JSON)
public class RestAuthResource {

    @GET
    @ApiOperation(
            value="Login with username/password",
            notes="API endpoint for authentication",
            response = String.class
    )
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Success with token")
    })
    @Path("/login") // http://localhost:8080/auth/login?username=user1&password=user1
    public String login(@ApiParam(value = "Username", required = true) @QueryParam("username") String username,
                        @ApiParam(value = "Password", required = true) @QueryParam("password") String  password) {

        return AuthenticateService.getInstance().login(username, password);
    }
}
