package narif.poc.wlseries.weblogicjms.restservice;

import narif.poc.wlseries.weblogicjms.model.CustomMsg;
import org.springframework.beans.factory.annotation.Autowired;
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
        prepareFinalMsgForDefaultQ(customMsg);
        jmsTemplate.convertAndSend(customMsg);
        return "Message Sent!";
    }

    private void prepareFinalMsgForDefaultQ(@RequestBody @Valid CustomMsg customMsg) {
        @NotNull @NotEmpty String msg = customMsg.getMessage();
        String finalMsg = msg + ". MSG FOR DEFAULT QUEUE.";
        customMsg.setMessage(finalMsg);
    }
}
