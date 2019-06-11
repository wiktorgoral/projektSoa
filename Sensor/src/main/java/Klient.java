import soap.MiejsceServiceSoap;
import soap.MiejsceServiceSoapService;

public class Klient {
    private static MiejsceServiceSoap init(){
        MiejsceServiceSoapService serwis = new MiejsceServiceSoapService();
        MiejsceServiceSoap port = serwis.getMiejsceServiceSoapPort();
        return port;

    }

    public static void ustawZajete(int id){
        MiejsceServiceSoap serwis = init();
        serwis.ustawZajete(id);
    }

    public static void ustawWolne(int id){
        MiejsceServiceSoap serwis = init();
        serwis.ustawWolne(id);
    }
}
