package rest;

import com.wordnik.swagger.annotations.*;
import core.Ticket;
import resource.NotAllowedException;
import services.BookingService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/book")
@Api(value = "/book") // Marks a class as a Swagger resource.
@Produces(MediaType.APPLICATION_JSON)
public class RestBookingResource {
    @GET
    @ApiOperation(
            value="Book and issue a ticket",
            notes="",
            response = Ticket.class
    )
    @ApiResponses(value = {
            @ApiResponse(code=200, message="the ticket containing all the information")
    })
    @Path("/ticket") // http://localhost:8080/book/ticket?token=XXXX&username=YYYY&id=MMMMM&id=ZZZZ
    public Ticket bookTicket(@ApiParam(value = "Authtoken", required = true) @QueryParam("token") String token,
                             @ApiParam(value = "Username", required = true) @QueryParam("username") String username,
                             @ApiParam(value = "Itinerary Id", required = true) @QueryParam("id") String id,
                             @ApiParam(value = "CC number", required = true) @QueryParam("id") String cc) {
        try {
            return BookingService.getInstance().bookTicket(token, username, id, cc);
        } catch (NotAllowedException e) {
            return null;
        }
    }
}
