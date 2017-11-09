package com.jms.outbound;

import com.jms.shared.Jms;
import com.jms.shared.Queue;

import javax.jms.*;
import java.util.Scanner;

public class JmsOutApplication {
    private static final String QUIT = "q";

    public static void main(String[] args) throws JMSException {
        try (Queue queue = Jms.getInstance().queue()) {
            final MessageProducer sender = queue.producer();
            final Scanner scanner = new Scanner(System.in);

            sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            while(scanner.hasNext()) {
                String message = scanner.next();

                if(message.equals(QUIT)) {
                    break;
                }

                sender.send(queue.createTextMessage(message));
            }
        }
    }
}