package com.jms.shared;

import com.jms.shared.domain.ConnectionProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.jms.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Queue implements AutoCloseable {
    private Connection connection;
    private Session session;
    private Destination destination;

    @SneakyThrows
    static Queue of(Connection connection, ConnectionProperties connectionProperties) {
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        final Destination destination = session.createQueue(connectionProperties.getQueue());

        return new Queue(connection, session, destination);
    }

    public Connection connection() {
        return connection;
    }

    public Destination destination() {
        return destination;
    }

    @SneakyThrows
    public MessageConsumer consumer() {
        return session.createConsumer(destination);
    }

    @SneakyThrows
    public MessageProducer producer() {
        return session.createProducer(destination);
    }

    @SneakyThrows
    public void start() {
        connection.start();
    }

    @Override
    @SneakyThrows
    public void close() {
        connection.close();
    }

    @SneakyThrows
    public void setExceptionListener(ExceptionListener exceptionListener) {
        connection.setExceptionListener(exceptionListener);
    }

    @SneakyThrows
    public Message createTextMessage(String message) {
        return session.createTextMessage(message);
    }
}