package pomelo.stream.consumer.rabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface IMessageConsumer {

	String DEFAULT_INPUT = "defaultInput";

	@Input(DEFAULT_INPUT)
	SubscribableChannel defaultInput();
}
