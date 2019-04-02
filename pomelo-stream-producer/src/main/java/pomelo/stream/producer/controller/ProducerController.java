package pomelo.stream.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pomelo.stream.producer.rabbit.provider.MessageProvider;

@RestController
public class ProducerController {

	@Autowired
	private MessageProvider msgProvider;

	@GetMapping("/produce/{msg}")
	public ResponseEntity<?> info(@PathVariable String msg) {
		msgProvider.sendMsg(msg);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
