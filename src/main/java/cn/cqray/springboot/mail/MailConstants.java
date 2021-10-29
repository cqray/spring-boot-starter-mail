package cn.cqray.springboot.mail;

/**
 * 邮件相关常量
 * @author Cqray
 */
final class MailConstants {

    static final String PREFIX = "mail_";
    static final String SERVICE_MAIL = PREFIX + "MailService";
    static final String CONFIGURATION_MAIL = PREFIX + "MailConfiguration";
    static final String PROTOCOL_SMTP = PREFIX + "MailSMTP";
    static final String PROTOCOL_POP3 = PREFIX + "MailPOP3";
    static final String PROTOCOL_IMAP = PREFIX + "MailIMAP";

    static final String PROPERTY_HOST = "${spring.mail.host:}";
    static final String PROPERTY_PORT = "${spring.mail.port:21}";
    static final String PROPERTY_USERNAME = "${spring.mail.username:}";
    static final String PROPERTY_PASSWORD = "${spring.mail.password:}";
    static final String PROPERTY_ENCODING = "${spring.mail.default-encoding:UTF-8}";
    static final String PROPERTY_DEBUG = "${spring.mail.properties.mail.debug:false}";
    static final String PROPERTY_AUTH_FOR_SMTP = "${spring.mail.properties.mail.smtp.auth:true}";
    static final String PROPERTY_AUTH_FOR_POP3 = "${spring.mail.properties.mail.pop3.auth:true}";
    static final String PROPERTY_AUTH_FOR_IMAP = "${spring.mail.properties.mail.imap.auth:true}";
    static final String PROPERTY_TIMEOUT_FOR_SMTP = "${spring.mail.properties.mail.smtp.timeout:}";
    static final String PROPERTY_TIMEOUT_FOR_POP3 = "${spring.mail.properties.mail.pop3.timeout:}";
    static final String PROPERTY_TIMEOUT_FOR_IMAP = "${spring.mail.properties.mail.imap.timeout:}";
    static final String PROPERTY_SOCKET_FACTORY_FOR_SMTP = "${spring.mail.properties.mail.smtp.socketFactory.class:default}";
    static final String PROPERTY_SOCKET_FACTORY_FOR_POP3 = "${spring.mail.properties.mail.pop3.socketFactory.class:default}";
    static final String PROPERTY_SOCKET_FACTORY_FOR_IMAP = "${spring.mail.properties.mail.imap.socketFactory.class:default}";

}
