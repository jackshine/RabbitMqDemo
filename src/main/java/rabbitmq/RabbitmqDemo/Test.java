package rabbitmq.RabbitmqDemo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Test {
	public Test() throws Exception{
        
        RabbitMqConsumer consumer = new RabbitMqConsumer("wwxtest");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
         
        RabbitMqProducer producer = new RabbitMqProducer("wwxtest");
         
        for (int i = 0; i < 1000; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number "+ i +" sent.");
        }
    }
     
    /**
     * @param args
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception{
      new Test();
    }
}
