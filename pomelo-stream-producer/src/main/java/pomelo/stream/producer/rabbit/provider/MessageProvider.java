package pomelo.stream.producer.rabbit.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import pomelo.stream.producer.rabbit.IMessageProvider;

@EnableBinding(IMessageProvider.class)
public class MessageProvider {

	@Autowired
	@Output(IMessageProvider.DEFAULT_OUTPUT)
	private MessageChannel channel;

	public void sendMsg(String msg) {
		channel.send(MessageBuilder.withPayload(msg).build());
		System.err.println("消息发送成功：" + msg);
	}
}
