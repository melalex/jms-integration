package com.jms.shared.connection;

import javax.jms.Connection;

public interface ConnectionProvider {

    Connection provide();
}
