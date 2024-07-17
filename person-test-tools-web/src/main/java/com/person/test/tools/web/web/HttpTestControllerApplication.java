package com.person.test.tools.web.web;

import com.person.test.tools.dubbo.consumer.DubboConsumer;
import com.person.test.tools.kafka.Producer.KafkaMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HttpTestControllerApplication {

    @Autowired
    DubboConsumer dubboConsumer;

    @Autowired
    private KafkaMessageProducer kafkaMessageProducer;

    @RequestMapping("/")
    @ResponseBody
    String test()
    {
       String tt = dubboConsumer.dubboCall();
        return tt;
    }

    @GetMapping("/publish")
    @ResponseBody
    public void publishAdminMessage(@RequestParam("message") String message) {
        kafkaMessageProducer.sendAdminMessage(message);
    }


}
