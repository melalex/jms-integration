package com.jms.shared.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ConnectionProperties {
    private int port;
    private int transportType;
    private String host;
    private String queueManager;
    private String channel;
    private String username;
    private String password;
    private String queue;
}