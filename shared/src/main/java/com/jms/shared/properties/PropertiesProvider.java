package com.jms.shared.properties;

import com.jms.shared.domain.ConnectionProperties;

import java.util.Properties;

public interface PropertiesProvider {

    ConnectionProperties provide(Properties properties);
}
