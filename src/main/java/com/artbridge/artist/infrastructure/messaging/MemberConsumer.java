package com.artbridge.artist.infrastructure.messaging;

import com.artbridge.artist.application.service.ArtistService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MemberConsumer {

    private final ArtistService artistService;

    private static final String TOPIC_MEMBER_NAME_REQUEST = "member-name";

    public MemberConsumer(ArtistService artistService) {
        this.artistService = artistService;
    }


    @KafkaListener(topics = TOPIC_MEMBER_NAME_REQUEST, groupId = "my-group")
    public void processMessage(String MemberNameDTOStr) {
        log.info("MemberConsumer: {}", MemberNameDTOStr);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();


        try {
            map = mapper.readValue(MemberNameDTOStr, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        artistService.modifyMemberName(Long.parseLong(map.get("id").toString()), map.get("name").toString());
        log.info("MemberNameRequestConsumer: {}", map.get("id"));
    }
}
