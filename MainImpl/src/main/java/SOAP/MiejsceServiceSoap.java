package SOAP;


import DAO.Miejsce;
import Warning.Warningi;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;

@Stateless
@WebService
public class MiejsceServiceSoap {

    @Inject
    Warningi alertManager;

    @WebMethod(operationName = "ustawZajete")
    public void ustawZajete(int id){
        Miejsce.ustawStan(id, false);
        alertManager.sprawdzMiejsce(id);
    }

    @WebMethod(operationName = "ustawWolne")
    public void ustawWolne(int id) {
        Miejsce.ustawStan(id, true);
        Date modifyDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateString = formatter.format(modifyDate);
        alertManager.wyslij("date:"+dateString);
    }
}
