package rest;

import com.wordnik.swagger.annotations.*;
import core.Itinerary;
import resource.NotAllowedException;
import services.ItineraryService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/itinerary")
@Api(value = "/itinerary") // Marks a class as a Swagger resource.
@Produces(MediaType.APPLICATION_JSON)
public class RestItineraryResource {
    @GET
    @ApiOperation(
            value="Retrieve itinerary by ID",
            notes="",
            response = Itinerary.class
    )
    @ApiResponses(value = {
            @ApiResponse(code=200, message="Contains the information about a trip")
    })
    @Path("/getitinerary") // http://localhost:8080/itinerary/getitinerary?token=XXXX&id=YYYY
    public Itinerary getItinerary(@ApiParam(value = "Authtoken", required = true) @QueryParam("token") String token,
                                  @ApiParam(value = "Itinerary Id", required = true) @QueryParam("id") String  id) {

        try {
            return ItineraryService.getInstance().getItinerary(token, id);
        } catch (NotAllowedException e) {
            return null;
        }
    }

    @GET
    @ApiOperation(
            value="Search for a trip based on departure and destination",
            notes="",
            response = List.class
    )
    @ApiResponses(value = {
            @ApiResponse(code=200, message="")
    })
    @Path("/search") // http://localhost:8080/itinerary/search?token=XXXX&source=YYYY&destination=ZZZZ
    public List<Itinerary> findItineraries(@ApiParam(value = "Authtoken", required = true) @QueryParam("token") String token,
                                           @ApiParam(value = "Departure city", required = true) @QueryParam("source") String  source,
                                           @ApiParam(value = "Arrival city", required = true) @QueryParam("destination") String  destination) {

        try {
            return ItineraryService.getInstance().findItineraries(token, source, destination);
        } catch (NotAllowedException e) {
            return null;
        }
    }
}
