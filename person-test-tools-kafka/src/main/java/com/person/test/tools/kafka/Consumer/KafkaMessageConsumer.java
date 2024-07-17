package com.person.test.tools.kafka.Consumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumer {

    @KafkaListener(topics = "admin-messages")
    public void receiveAdminMessage(String message) {
        System.out.println("Received message: " + message);
        // ...
    }
}
