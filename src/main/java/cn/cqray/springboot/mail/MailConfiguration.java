package cn.cqray.springboot.mail;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 邮箱配置信息
 * @author Cqray
 */
@Configuration(value = MailConstants.CONFIGURATION_MAIL)
@ComponentScan(basePackageClasses = {MailConfiguration.class})
class MailConfiguration {

    private final MailProtocolSMTP mailProtocolSMTP;
    private final MailProtocolPOP3 mailProtocolPOP3;
    private final MailProtocolIMAP mailProtocolIMAP;
    private Mail mail;

    public MailConfiguration(MailProtocolSMTP mailProtocolSMTP,
                             MailProtocolPOP3 mailProtocolPOP3,
                             MailProtocolIMAP mailProtocolIMAP) {
        this.mailProtocolSMTP = mailProtocolSMTP;
        this.mailProtocolPOP3 = mailProtocolPOP3;
        this.mailProtocolIMAP = mailProtocolIMAP;
        this.mail = new Mail().mix(mailProtocolSMTP);
    }

    @Autowired(required = false)
    void setMail(@NotNull Mail mail) {
        switch (mail.getProtocol()) {
            case IMAP:
                this.mail = mail.mix(mailProtocolIMAP);
                break;
            case POP3:
                this.mail = mail.mix(mailProtocolPOP3);
                break;
            default:
                this.mail = mail.mix(mailProtocolSMTP);
        }
    }

    public Mail getMail() {
        return mail;
    }

}
