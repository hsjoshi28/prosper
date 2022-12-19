//package com.Prosper.service;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
//import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
//
//@Configuration
//@Component
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
//    	registry.addEndpoint("/ws")
//    	.setHandshakeHandler(new DefaultHandshakeHandler(new TomcatRequestUpgradeStrategy()))
//    	.withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.setApplicationDestinationPrefixes("/app");
//        registry.enableSimpleBroker("/chatroom","/user");
//        registry.setUserDestinationPrefix("/user");
//    }
//}
