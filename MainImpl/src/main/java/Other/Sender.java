package Other;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class Sender {

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jboss/exported/jms/queue/SOA_test")
    private Queue queue;

    public void sendMessage(String txt){
        try {
            context.createProducer().send(queue, txt);
            System.out.println("Wysłano " + txt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
