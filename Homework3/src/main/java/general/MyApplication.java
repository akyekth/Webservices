package general;


import com.roskart.dropwizard.jaxws.EndpointBuilder;
import com.roskart.dropwizard.jaxws.JAXWSBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;
import resource.AuthServiceResourceImpl;
import resource.BookingServiceResourceImpl;
import resource.ItineraryServiceResourceImpl;
import rest.RestAuthResource;
import rest.RestBookingResource;
import rest.RestItineraryResource;

public class MyApplication extends Application<MyApplicationConfiguration> {

    private JAXWSBundle jaxwsBundle = new JAXWSBundle();
    private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();

    @Override
    public void initialize(Bootstrap<MyApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(jaxwsBundle);
        // bootstrap.addBundle(new SwaggerBundle<MyApplicationConfiguration>());
        swaggerDropwizard.onInitialize(bootstrap);
    }

    @Override
    public void run(MyApplicationConfiguration configuration, Environment environment) throws Exception {
        jaxwsBundle.publishEndpoint(
                new EndpointBuilder("/auth/login", new AuthServiceResourceImpl()));

        jaxwsBundle.publishEndpoint(
                new EndpointBuilder("/itinerary", new ItineraryServiceResourceImpl()));

        jaxwsBundle.publishEndpoint(
                new EndpointBuilder("/book", new BookingServiceResourceImpl()));

        final RestAuthResource authResource = new RestAuthResource();
        environment.jersey().register(authResource);

        final RestItineraryResource itineraryResource = new RestItineraryResource();
        environment.jersey().register(itineraryResource);

        final RestBookingResource bookResource = new RestBookingResource();
        environment.jersey().register(bookResource);

        swaggerDropwizard.onRun(configuration, environment, "localhost");
    }

    public static void main(String args[]) throws Exception {
        new MyApplication().run(args);
    }
}
