package com.isignthis.consumer.controller;

import com.isignthis.consumer.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
public class KafkaConsumerController
{

    private final SimpMessagingTemplate template;

    private Set<String> listeners = new HashSet<>();

    public KafkaConsumerController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void add(String sessionId) {
        listeners.add(sessionId);
    }

    public void remove(String sessionId) {
        listeners.remove(sessionId);
    }

    public void dispatch( final String msg )
    {
        for (String listener : listeners)
        {
            log.info("Sending notification to " + listener);

            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
            headerAccessor.setSessionId(listener);
            headerAccessor.setLeaveMutable(true);

            template.convertAndSendToUser(
                    listener,
                    "/notification/item",
                    new Notification( msg ),
                    headerAccessor.getMessageHeaders());
        }
    }

    //	@KafkaListener(topics = "topic1, topic2", groupId = "foo")
    @KafkaListener( topics = "example-topic" )
    public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition )
    {
        final String msg = String.format("Received Message: %s from partition: %s", message, partition );
        dispatch( msg );
        log.info(  msg );
    }

}
