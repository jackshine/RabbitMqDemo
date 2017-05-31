package rabbitmq.RabbitmqDemo;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;

/**
 * @ClassName: RabbitMqProducer 
 * @Description: 生产者
 * @author wwx
 * @date 2017年5月31日 下午4:31:32 
 *
 */
public class RabbitMqProducer extends RabbitConfig {
	public RabbitMqProducer(String queueName) throws IOException {
		super(queueName);
	}

	public void sendMessage(Serializable object) throws IOException {
		channel.basicPublish("", queueName, null, SerializationUtils.serialize(object));
	}
}
