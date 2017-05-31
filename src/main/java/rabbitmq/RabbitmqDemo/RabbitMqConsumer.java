package rabbitmq.RabbitmqDemo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * @ClassName: RabbitMqConsumer 
 * @Description: 消费者 
 * @author wwx
 * @date 2017年5月31日 下午4:31:53 
 *
 */
public class RabbitMqConsumer  extends RabbitConfig implements Runnable, Consumer{
	 public RabbitMqConsumer(String queueName) throws IOException{
	        super(queueName);       
	    }
	     
	    public void run() {
	        try {
	            //开始消费消息 自动确认消息。
	            channel.basicConsume(queueName, true,this);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    /**
	     * 消费者注册时被调用
	     */
	    public void handleConsumeOk(String consumerTag) {
	        System.out.println("Consumer "+consumerTag +" registered");    
	    }
	 
	    /**
	     * 当新消息消费时调用。
	     */
	    public void handleDelivery(String consumerTag, Envelope env,
	            BasicProperties props, byte[] body) throws IOException {
	        Map map = (HashMap)SerializationUtils.deserialize(body);
	        System.out.println("Message Number "+ map.get("message number") + " received.");
	    }
	 
	    public void handleCancel(String consumerTag) {}
	    public void handleCancelOk(String consumerTag) {}
	    public void handleRecoverOk(String consumerTag) {}
	    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}
}
