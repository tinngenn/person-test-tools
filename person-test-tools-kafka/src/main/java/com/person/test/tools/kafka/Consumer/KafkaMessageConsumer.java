package com.person.test.tools.kafka.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageConsumer {

    @KafkaListener(topics = "admin-messages")
    public void receiveAdminMessage(String message) {
        System.out.println("Received message: " + message);
        log.info("Received message: {}" ,message);
        // ...
    }
}
