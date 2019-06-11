package Other;


import Controller.BazaWiadomosci;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

@MessageDriven(mappedName= "java:/jboss/exported/jms/queue/SOA_test")
public class Receiver implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(Receiver.class.toString());

    @EJB(lookup = "java:global/MainImpl/BazaWiadomosci!Warningi.local.WiadomosciLocal")
    BazaWiadomosci bazaWiadomosci;

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
