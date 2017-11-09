package com.jms.shared;

import com.jms.shared.connection.ConnectionProvider;
import com.jms.shared.connection.impl.ConnectionProviderImpl;
import com.jms.shared.domain.ConnectionProperties;
import com.jms.shared.properties.PropertiesProvider;
import com.jms.shared.properties.impl.PropertiesProviderImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.jms.Connection;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Jms {
    private static final String PROPERTIES = "properties";

    private static PropertiesProvider propertiesProvider = new PropertiesProviderImpl();
    private static Jms ourInstance = createJms();

    private ConnectionProvider connectionProvider;

    @Getter
    private ConnectionProperties properties;

    public static Jms getInstance() {
        return ourInstance;
    }

    private static Jms createJms() {
        Jms jms = new Jms();
        jms.properties = properties();
        jms.connectionProvider = ConnectionProviderImpl.of(jms.properties);

        return jms;
    }

    private static ConnectionProperties properties() {
        Properties properties = System.getProperties();

        return properties.containsKey(PROPERTIES)
                ? propertiesProvider.provide(load(properties.getProperty(PROPERTIES)))
                : propertiesProvider.provide(properties);
    }

    @SneakyThrows
    private static Properties load(String name) {
        try (final InputStream stream = new FileInputStream(name)) {
            final Properties properties = new Properties();
            properties.load(stream);

            return properties;
        }
    }

    public Connection connection() {
        return connectionProvider.provide();
    }

    public Queue queue() {
        return Queue.of(connectionProvider.provide(), properties);
    }
}