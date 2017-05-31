package rabbitmq.RabbitmqDemo;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @ClassName: RabbitConfig 
 * @Description: 基础配置
 * @author wwx
 * @date 2017年5月31日 下午5:14:37 
 *
 */
public class RabbitConfig 
{
	protected Channel channel;
    protected Connection connection;
    protected String queueName;
     
    public RabbitConfig(String queueName) throws IOException{
         this.queueName = queueName;
         
         /** 
         * 创建连接连接到MabbitMQ 
         */  
         ConnectionFactory factory = new ConnectionFactory();
         
         //设置MabbitMQ所在主机ip或者主机名  
         factory.setHost("192.168.43.128");
         factory.setPort(5672);
         factory.setUsername("root");
         factory.setPassword("123456");
         
         //创建一个连接  
         connection = factory.newConnection();
         
          //创建一个频道 
         channel = connection.createChannel();
         
         //声明此通道的队列。 如果队列不存在，它将在服务器上创建。
         channel.queueDeclare(queueName, false, false, false, null);
          
    }
     
     
    /**
     * 关闭channel和connection。并非必须，因为隐含是自动调用的。
     * @throws IOException
     */
     public void close() throws IOException{
         this.channel.close();
         this.connection.close();
     }
}
