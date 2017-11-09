package com.jms.inbound.listners;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class JmsListener implements MessageListener, ExceptionListener {

    @Override
    public void onException(JMSException exception) {
        exception.printStackTrace();
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.printf("Received message: %s", message.getBody(String.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}