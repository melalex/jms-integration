package com.jms.inbound;

import com.jms.inbound.listners.JmsListener;
import com.jms.shared.Jms;
import com.jms.shared.Queue;

import javax.jms.*;
import java.util.Scanner;

public class JmsInApplication {
    private static final String QUIT = "q";

    public static void main(String[] args) throws JMSException {
        try (Queue queue = Jms.getInstance().queue()) {
            final JmsListener listener = new JmsListener();
            final MessageConsumer consumer = queue.consumer();
            final Scanner scanner = new Scanner(System.in);

            queue.setExceptionListener(listener);
            consumer.setMessageListener(listener);

            while (scanner.hasNext()) {
                if(scanner.next().equals(QUIT)) {
                    break;
                }
            }
        }
    }
}