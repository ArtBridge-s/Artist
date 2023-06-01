package com.artbridge.artist.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Slf4j
public class MemberProducerImpl implements MemberProducer{


    // 토픽명
    private static final String TOPIC_MEMBERNAME = "topic_membername";

    private final KafkaProperties kafkaProperties;
    private KafkaProducer<String, String> producer;

    public MemberProducerImpl(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @PostConstruct
    public void initialize() {
        // KafkaProducer 객체 생성
        log.info("Kafka Producer Initializing...");
        this.producer = new KafkaProducer<>(kafkaProperties.buildProducerProperties());
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
        log.info("Kafka Producer Initialized");
    }


    @Override
    public void requestMemberName(Long id) {
        // KafkaProducer 객체를 이용하여 토픽에 메시지 전송
        log.info("Request MemberName to Kafka Producer");
        producer.send(new ProducerRecord<>(TOPIC_MEMBERNAME, id.toString()));
    }

    @PreDestroy
    public void shutdown() {
        log.info("Shutdown Kafka Producer...");
        producer.close();
    }

}
