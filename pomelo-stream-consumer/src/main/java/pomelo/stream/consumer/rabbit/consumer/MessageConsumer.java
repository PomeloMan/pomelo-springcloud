package pomelo.stream.consumer.rabbit.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import pomelo.stream.consumer.rabbit.IMessageConsumer;

@EnableBinding(IMessageConsumer.class)
public class MessageConsumer {

	@StreamListener(IMessageConsumer.DEFAULT_INPUT)
	public void messageInPut(Message<String> message) {
		System.out.println(" 消息接收成功：" + message.getPayload());
	}
}
