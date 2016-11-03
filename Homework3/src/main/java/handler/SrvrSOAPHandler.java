package handler;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;


public class SrvrSOAPHandler implements SOAPHandler<SOAPMessageContext> {

    @PostConstruct
    public void init() {}

    public Set<QName> getHeaders() {
        return null;
    }


    public boolean handleMessage(SOAPMessageContext context) {
        boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if(outbound) {
            System.out.println("=====OUTBOUND MESSAGE=====");
            try {
                SOAPMessage message = ((SOAPMessageContext) context).getMessage();
                SOAPPart part = message.getSOAPPart();
                SOAPEnvelope env = part.getEnvelope();
                SOAPBody body = env.getBody();
                //dumpSOAPMessage(msg);
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("=====INBOUND MESSAGE=====");
            SOAPMessage msg = ((SOAPMessageContext) context).getMessage();
            //dumpSOAPMessage(msg);
        }

        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {

    }
}
