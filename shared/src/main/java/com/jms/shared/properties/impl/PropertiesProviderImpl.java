package com.jms.shared.properties.impl;

import com.jms.shared.domain.ConnectionProperties;
import com.jms.shared.properties.PropertiesProvider;

import java.util.Properties;

public class PropertiesProviderImpl implements PropertiesProvider {
    private static final String HOST = "host";
    private static final String PORT = "port";
    private static final String QUEUE_MANAGER = "queueManager";
    private static final String CHANNEL = "channel";
    private static final String TRANSPORT_TYPE = "transport";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String QUEUE = "queue";

    @Override
    public ConnectionProperties provide(Properties properties) {
        return ConnectionProperties.builder()
                .host(properties.getProperty(HOST))
                .port(Integer.valueOf(properties.getProperty(PORT)))
                .queueManager(properties.getProperty(QUEUE_MANAGER))
                .channel(properties.getProperty(CHANNEL))
                .transportType(Integer.valueOf(properties.getProperty(TRANSPORT_TYPE)))
                .username(properties.getProperty(USERNAME))
                .password(properties.getProperty(PASSWORD))
                .queue(properties.getProperty(QUEUE))
                .build();
    }
}
