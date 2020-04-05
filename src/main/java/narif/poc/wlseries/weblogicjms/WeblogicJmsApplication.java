package narif.poc.wlseries.weblogicjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;

@SpringBootApplication(exclude = ActiveMQAutoConfiguration.class)
public class WeblogicJmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeblogicJmsApplication.class, args);
	}

}
