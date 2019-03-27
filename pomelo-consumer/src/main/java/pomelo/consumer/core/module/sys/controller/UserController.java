package pomelo.consumer.core.module.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import feign.Client;
import feign.Contract;
import feign.Logger.Level;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.hystrix.HystrixFeign;
import pomelo.consumer.core.module.sys.persistence.entity.User;
import pomelo.consumer.feign.ProducerFeignClient;
import pomelo.consumer.feign.callback.ProducerFeignClientFallback;

@Import(FeignClientsConfiguration.class)
@RestController
@RequestMapping("/user")
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	private ProducerFeignClient producerFeignClient;

	@Autowired
	public UserController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
		// HystrixFeign.builder() / Feign.builder()
		this.producerFeignClient = HystrixFeign.builder().client(client).encoder(encoder).decoder(decoder)
				.contract(contract).logLevel(Level.FULL)
				.logger(new feign.Logger.JavaLogger().appendToFile("logs\\feign.log"))
				.requestInterceptor(new BasicAuthRequestInterceptor("pomelo", "pomelo"))
				.target(ProducerFeignClient.class, "http://pomelo-producer/", new ProducerFeignClientFallback());
	}

	@GetMapping("/{username}")
	@HystrixCommand(fallbackMethod = "infoFallback")
	public ResponseEntity<User> info(@PathVariable String username) {
		return new ResponseEntity<User>(
				this.restTemplate.getForObject("http://pomelo-producer/user/" + username, User.class), HttpStatus.OK);
	}

	public ResponseEntity<User> infoFallback(String username) {
		return new ResponseEntity<User>(new User(), HttpStatus.OK);
	}

	@GetMapping("/feign/{username}")
	public ResponseEntity<User> feignInfo(@PathVariable String username) {
		return new ResponseEntity<User>(this.producerFeignClient.findUser(username), HttpStatus.OK);
	}

	@GetMapping("/log-instance")
	public void logUserInstance() {
		ServiceInstance instance = this.loadBalancerClient.choose("pomelo-producer");
		logger.info("{}:{}:{}", instance.getServiceId(), instance.getHost(), instance.getPort());
	}
}
