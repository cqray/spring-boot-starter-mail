package cn.cqray.springboot.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮箱配置信息
 * @author Cqray
 */
@Component(value = MailConstants.PROTOCOL_IMAP)
final class MailProtocolIMAP extends MailProtocolBase {

    @Value(MailConstants.PROPERTY_AUTH_FOR_IMAP)
    private boolean auth;
    @Value(MailConstants.PROPERTY_TIMEOUT_FOR_IMAP)
    private Long timeout;
    @Value(MailConstants.PROPERTY_SOCKET_FACTORY_FOR_IMAP)
    private String socketFactory;

    @Override
    public boolean isAuth() {
        return auth;
    }

    @Override
    public Long getTimeout() {
        return timeout;
    }

    @Override
    public String getSocketFactory() {
        return socketFactory;
    }
}
