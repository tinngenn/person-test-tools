package com.person.test.tools.web.web;

import com.person.test.tools.dubbo.consumer.DubboConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HttpTestControllerApplication {

    @Autowired
    DubboConsumer dubboConsumer;

    @RequestMapping("/")
    @ResponseBody
    String test()
    {
       String tt = dubboConsumer.dubboCall();
        return tt;
    }
}
