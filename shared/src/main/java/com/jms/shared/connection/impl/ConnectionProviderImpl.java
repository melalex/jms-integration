package com.jms.shared.connection.impl;

import com.ibm.mq.jms.MQConnectionFactory;
import com.jms.shared.connection.ConnectionProvider;
import com.jms.shared.domain.ConnectionProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionProviderImpl implements ConnectionProvider {
    private ConnectionProperties properties;
    private ConnectionFactory connectionFactory;

    @SneakyThrows
    public static ConnectionProviderImpl of(ConnectionProperties properties) {
        final MQConnectionFactory connectionFactory = new MQConnectionFactory();

        connectionFactory.setHostName(properties.getHost());
        connectionFactory.setPort(properties.getPort());
        connectionFactory.setQueueManager(properties.getQueueManager());
        connectionFactory.setChannel(properties.getChannel());
        connectionFactory.setTransportType(properties.getTransportType());

        return new ConnectionProviderImpl(properties, connectionFactory);
    }

    @Override
    @SneakyThrows
    public Connection provide() {
        return connectionFactory.createConnection(properties.getUsername(), properties.getPassword());
    }
}