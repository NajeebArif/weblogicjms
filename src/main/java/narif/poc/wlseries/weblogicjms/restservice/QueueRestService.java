package narif.poc.wlseries.weblogicjms.restservice;

import narif.poc.wlseries.weblogicjms.model.CustomMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "queue")
public class QueueRestService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping
    public String postMessage(@RequestBody @Valid CustomMsg customMsg){
        prepareFinalMsg(customMsg, "<MSG FOR DEFAULT QUEUE>");
        jmsTemplate.convertAndSend(customMsg);
        return "Message Sent!";
    }

    @Value("${jms.queue.jndi-name}")
    private String distributedQueueJndiName;

    @PostMapping("dq")
    public String postMessageToDq(@RequestBody @Valid CustomMsg customMsg){
        prepareFinalMsg(customMsg, "<MSG FOR DISTRIBUTED QUEUE>");
        jmsTemplate.convertAndSend(distributedQueueJndiName, customMsg);
        return "Message Sent to Distributed Queue!";
    }

    private void prepareFinalMsg(CustomMsg customMsg, String trailingMsg) {
        @NotNull @NotEmpty String msg = customMsg.getMessage();
        String finalMsg = msg + trailingMsg;
        customMsg.setMessage(finalMsg);
    }

}
