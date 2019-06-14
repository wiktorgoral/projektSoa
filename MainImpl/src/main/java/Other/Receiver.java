package Other;


import Controller.BazaWiadomosci;
import Warning.Wiadomosci;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;



@MessageDriven( activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jboss/exported/jms/queue/SOA_test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class Receiver implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(Receiver.class.toString());


    Wiadomosci bazaWiadomosci = new BazaWiadomosci();

    public void onMessage(Message message) {
        TextMessage text=null;
        try{
            if (message instanceof TextMessage){
                text = (TextMessage) message;
                LOGGER.info("Nowa wiadomosc z kolejki " + text.getText());
                bazaWiadomosci.dodaj(text.getText());
            }
            else LOGGER.warning("Zly typ "+ message.getClass().getName() );
        }
        catch (JMSException e){
            throw new RuntimeException(e);
        }
    }
}
