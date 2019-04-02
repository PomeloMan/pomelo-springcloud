package pomelo.stream.producer.rabbit;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface IMessageProvider {

	String DEFAULT_OUTPUT = "defaultOutput";

	@Output(DEFAULT_OUTPUT)
	MessageChannel defaultOutput();
}
