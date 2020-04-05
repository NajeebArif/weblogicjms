package narif.poc.wlseries.weblogicjms.listeners;

import narif.poc.wlseries.weblogicjms.model.CustomMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Component
@Transactional
public class JmsQueueListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsQueueListener.class.getName());

    @JmsListener(destination = "jms/myTestQueue")
    public void listenToMessages(@Valid CustomMsg customMsg){
        logTheCustomMsg(customMsg);
    }

    @JmsListener(destination = "${jms.queue.jndi-name}")
    public void receiveDistributedQueueMsg(@Valid CustomMsg customMsg){
        logTheCustomMsg(customMsg);
    }

    private void logTheCustomMsg(@Valid CustomMsg customMsg) {
        String text = "MESSAGE RECEIVED AT: " + LocalDateTime.now() + ". MSG: " + customMsg;
        LOGGER.info(text);
    }
}
