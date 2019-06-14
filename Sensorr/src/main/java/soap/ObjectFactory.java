
package soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UstawZajete_QNAME = new QName("http://SOAP/", "ustawZajete");
    private final static QName _UstawZajeteResponse_QNAME = new QName("http://SOAP/", "ustawZajeteResponse");
    private final static QName _UstawWolne_QNAME = new QName("http://SOAP/", "ustawWolne");
    private final static QName _UstawWolneResponse_QNAME = new QName("http://SOAP/", "ustawWolneResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UstawZajeteResponse }
     * 
     */
    public UstawZajeteResponse createUstawZajeteResponse() {
        return new UstawZajeteResponse();
    }

    /**
     * Create an instance of {@link UstawZajete }
     * 
     */
    public UstawZajete createUstawZajete() {
        return new UstawZajete();
    }

    /**
     * Create an instance of {@link UstawWolneResponse }
     * 
     */
    public UstawWolneResponse createUstawWolneResponse() {
        return new UstawWolneResponse();
    }

    /**
     * Create an instance of {@link UstawWolne }
     * 
     */
    public UstawWolne createUstawWolne() {
        return new UstawWolne();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UstawZajete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP/", name = "ustawZajete")
    public JAXBElement<UstawZajete> createUstawZajete(UstawZajete value) {
        return new JAXBElement<UstawZajete>(_UstawZajete_QNAME, UstawZajete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UstawZajeteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP/", name = "ustawZajeteResponse")
    public JAXBElement<UstawZajeteResponse> createUstawZajeteResponse(UstawZajeteResponse value) {
        return new JAXBElement<UstawZajeteResponse>(_UstawZajeteResponse_QNAME, UstawZajeteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UstawWolne }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP/", name = "ustawWolne")
    public JAXBElement<UstawWolne> createUstawWolne(UstawWolne value) {
        return new JAXBElement<UstawWolne>(_UstawWolne_QNAME, UstawWolne.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UstawWolneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP/", name = "ustawWolneResponse")
    public JAXBElement<UstawWolneResponse> createUstawWolneResponse(UstawWolneResponse value) {
        return new JAXBElement<UstawWolneResponse>(_UstawWolneResponse_QNAME, UstawWolneResponse.class, null, value);
    }

}
