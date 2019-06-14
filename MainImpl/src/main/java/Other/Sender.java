package Other;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

@Stateless
public class Sender {

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jboss/exported/jms/queue/SOA_test")
    private Queue queue;

    public void sendMessage(String txt){
        try {
            TextMessage msg = context.createTextMessage(txt);
            context.createProducer().send(queue, msg);
            System.out.println("Wysłano " + txt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
