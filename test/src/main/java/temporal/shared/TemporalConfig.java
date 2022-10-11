package temporal.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class TemporalConfig {
	public static String ADDR = "localhost";
	public static String PORT = "8080";
	public static String DOMAIN = "default";
	public static String HELLO_WORLD_TASK_QUEUE = "HELLO_WORLD_TASK_QUEUE";
	
	
	
	/*
	@Value("${temporal.address}")
	private String TEMPORAL_ADDRESS;
	@Value("${temporal.port")
	private String TEMPORAL_PORT; */
	/*
	@Value("${temporal.address}")
	public void setAddr(String addr) {
		TemporalConfig.ADDR = addr;
	}
	@Value("${temporal.port}")
	public void setPort(String port) {
		TemporalConfig.PORT = port;
	} */
}
