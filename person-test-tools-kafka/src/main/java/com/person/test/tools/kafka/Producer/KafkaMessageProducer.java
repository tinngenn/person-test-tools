package com.person.test.tools.kafka.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducer {

    private static final String TOPIC = "admin-messages";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendAdminMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}