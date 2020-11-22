package com.consumer.configs;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

public class WebSocketKafkaConfig implements WebSocketMessageBrokerConfigurer
{
    @Override
    public void registerStompEndpoints( StompEndpointRegistry registry )
    {
        registry.addEndpoint( "/kafka-websocket" ).withSockJS();
    }

    @Override
    public void configureMessageBroker( MessageBrokerRegistry registry )
    {
        registry.enableSimpleBroker("/topick" );
        registry.setApplicationDestinationPrefixes( "/app" );
    }
}
