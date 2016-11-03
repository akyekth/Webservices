
package resource;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BookingServiceResourceImplService", targetNamespace = "http://resource/", wsdlLocation = "http://localhost:8080/soap/book?WSDL")
public class BookingServiceResourceImplService
    extends Service
{

    private final static URL BOOKINGSERVICERESOURCEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException BOOKINGSERVICERESOURCEIMPLSERVICE_EXCEPTION;
    private final static QName BOOKINGSERVICERESOURCEIMPLSERVICE_QNAME = new QName("http://resource/", "BookingServiceResourceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/soap/book?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BOOKINGSERVICERESOURCEIMPLSERVICE_WSDL_LOCATION = url;
        BOOKINGSERVICERESOURCEIMPLSERVICE_EXCEPTION = e;
    }

    public BookingServiceResourceImplService() {
        super(__getWsdlLocation(), BOOKINGSERVICERESOURCEIMPLSERVICE_QNAME);
    }

    public BookingServiceResourceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BOOKINGSERVICERESOURCEIMPLSERVICE_QNAME, features);
    }

    public BookingServiceResourceImplService(URL wsdlLocation) {
        super(wsdlLocation, BOOKINGSERVICERESOURCEIMPLSERVICE_QNAME);
    }

    public BookingServiceResourceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BOOKINGSERVICERESOURCEIMPLSERVICE_QNAME, features);
    }

    public BookingServiceResourceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BookingServiceResourceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BookingServiceResource
     */
    @WebEndpoint(name = "BookingServiceResourceImplPort")
    public BookingServiceResource getBookingServiceResourceImplPort() {
        return super.getPort(new QName("http://resource/", "BookingServiceResourceImplPort"), BookingServiceResource.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BookingServiceResource
     */
    @WebEndpoint(name = "BookingServiceResourceImplPort")
    public BookingServiceResource getBookingServiceResourceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://resource/", "BookingServiceResourceImplPort"), BookingServiceResource.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BOOKINGSERVICERESOURCEIMPLSERVICE_EXCEPTION!= null) {
            throw BOOKINGSERVICERESOURCEIMPLSERVICE_EXCEPTION;
        }
        return BOOKINGSERVICERESOURCEIMPLSERVICE_WSDL_LOCATION;
    }

}