package temporal.shared;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class TemporalConfig {
	//the docker-compose image will use this as default address
	public static final String ADDR = "localhost";
	public static final String PORT = "7233";
	//default domain
	public static final String DOMAIN = "default";
	//queue where the "helloworld workflow" places workflows to be executed & where workers will go find them
	public static final String HELLO_WORLD_TASK_QUEUE = "HELLO_WORLD_TASK_QUEUE";
	
}
