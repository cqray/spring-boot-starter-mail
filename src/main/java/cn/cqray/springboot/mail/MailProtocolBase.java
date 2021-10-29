package cn.cqray.springboot.mail;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * 基础Mail配置读取
 * @author Cqray
 */
abstract class MailProtocolBase implements Serializable {

    @Value(MailConstants.PROPERTY_HOST)
    private String host;
    @Value(MailConstants.PROPERTY_PORT)
    private int port;
    @Value(MailConstants.PROPERTY_USERNAME)
    private String username;
    @Value(MailConstants.PROPERTY_PASSWORD)
    private String password;
    @Value(MailConstants.PROPERTY_ENCODING)
    private String encoding;
    @Value(MailConstants.PROPERTY_DEBUG)
    private boolean debug;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEncoding() {
        return encoding;
    }

    public boolean isDebug() {
        return debug;
    }

    public abstract boolean isAuth();

    public abstract Long getTimeout();

    public abstract String getSocketFactory();
}
