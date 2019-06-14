
package soap;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MiejsceServiceSoap", targetNamespace = "http://SOAP/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MiejsceServiceSoap {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "ustawWolne", targetNamespace = "http://SOAP/", className = "soap.UstawWolne")
    @ResponseWrapper(localName = "ustawWolneResponse", targetNamespace = "http://SOAP/", className = "soap.UstawWolneResponse")
    public void ustawWolne(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

    /**
     *
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "ustawZajete", targetNamespace = "http://SOAP/", className = "soap.UstawZajete")
    @ResponseWrapper(localName = "ustawZajeteResponse", targetNamespace = "http://SOAP/", className = "soap.UstawZajeteResponse")
    public void ustawZajete(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

}
