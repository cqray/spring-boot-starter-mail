package cn.cqray.springboot.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮箱配置信息
 * @author Cqray
 */
@Component(value = MailConstants.PROTOCOL_POP3)
final class MailProtocolPOP3 extends MailProtocolBase {

    @Value(MailConstants.PROPERTY_AUTH_FOR_POP3)
    private boolean auth;
    @Value(MailConstants.PROPERTY_TIMEOUT_FOR_POP3)
    private Long timeout;
    @Value(MailConstants.PROPERTY_SOCKET_FACTORY_FOR_POP3)
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
