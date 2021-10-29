package cn.cqray.springboot.mail;

import org.jetbrains.annotations.NotNull;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 邮箱服务
 * @author Cqray
 */
@Service(value = MailConstants.SERVICE_MAIL)
public class MailService {

    /** 配置文件配置参数 **/
    private final MailConfiguration mailConfiguration;

    public MailService(@NotNull MailConfiguration mailConfiguration) {
        this.mailConfiguration = mailConfiguration;
        getProperties(mailConfiguration.getMail());
    }

    /**
     * 发送邮件
     * @param dto 邮件内容
     * @return 发送结果
     */
    public MailSendResult send(@NotNull MailDto dto) {
        return send(mailConfiguration.getMail(), dto);
    }

    /**
     * 发送邮件
     * @param mail 邮件配置
     * @param dto 邮件内容
     * @return 发送结果
     */
    @NotNull
    public MailSendResult send(@NotNull Mail mail, @NotNull MailDto dto) {
        return send(mail, session -> createMimeMessage(session, mail, dto));
    }

    /**
     * 发送邮件
     * @param mail 邮件配置
     * @param provider 邮件消息提供者
     * @return 发送结果
     */
    @NotNull
    public MailSendResult send(@NotNull Mail mail, MessageProvider provider) {
        String protocol = mail.getProtocol().name().toLowerCase();
        Session session = getSession(mail);
        Transport ts;
        try {
            ts = session.getTransport(protocol);
            ts.connect(mail.getHost(), mail.getPort(), mail.getUsername(), mail.getPassword());
        } catch (NoSuchProviderException ignored) {
            return new MailSendResult("不支持协议" + protocol.toUpperCase());
        } catch (MessagingException ignored) {
            return new MailSendResult("邮件服务器身份验证失败");
        }
        try {
            MimeMessage message = provider.getMessage(session);
            message.saveChanges();
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
            return new MailSendResult();
        } catch (UnsupportedEncodingException ignored) {
            return new MailSendResult("不支持协议" + protocol.toUpperCase());
        } catch (MessagingException ignored) {
            return new MailSendResult("邮件发送失败");
        }
    }

    /**
     * 获取Mail配置信息
     * @return Mail配置
     */
    public Mail getMail() {
        return mailConfiguration.getMail();
    }

    /**
     * 获取Session
     * @param mail Mail
     * @return Session
     */
    public Session getSession(@NotNull Mail mail) {
        Properties prop = getProperties(mail);
        Session session = Session.getInstance(prop);
        mail.debug(mail.isDebug());
        session.setDebug(mail.isDebug());
        return session;
    }

    /**
     * 根据Mail获取配置属性
     * @param mail Mail
     * @return 配置属性
     */
    @NotNull
    public Properties getProperties(@NotNull Mail mail) {
        final String sslFactory = "javax.net.ssl.SSLSocketFactory";
        String protocol = mail.getProtocol().name().toLowerCase();
        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", protocol);
        prop.setProperty(String.format("mail.%s.auth", protocol), mail.isAuth() + "");
        // 超时时间
        if (mail.getTimeout() != null) {
            prop.setProperty(String.format("mail.%s.timeout", protocol), mail.getTimeout() + "");
        }
        // 是否开启SSL
        if (mail.isSsl()) {
            prop.setProperty(String.format("mail.%s.socketFactory.class", protocol), sslFactory);
        }
        return prop;
    }

    /**
     * 创建MimeMessage
     * @param session Session
     * @param mail Mail
     * @param dto MailDto
     * @return 返回MimeMessage
     * @throws MessagingException 邮箱内容设置失败
     * @throws UnsupportedEncodingException 不支持编码
     */
    @NotNull
    public MimeMessage createMimeMessage(Session session, @NotNull Mail mail, @NotNull MailDto dto)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, mail.getEncoding());
        InternetAddress address = new InternetAddress(mail.getUsername(), mail.getNickname(), mail.getEncoding());
        // 发送人
        mmh.setFrom(address);
        // 收件人
        mmh.setTo(dto.getTo());
        // 抄送人
        if (dto.getCc() != null) {
            mmh.setCc(dto.getCc());
        }
        // 密送人
        if (dto.getBcc() != null) {
            mmh.setBcc(dto.getBcc());
        }
        // 主题
        mmh.setSubject(dto.getSubject());
        // 内容
        mmh.setText(dto.getContent(), dto.isHtml());
        // 发送时间
        mmh.setSentDate(new Date());
        // 附件
        if (dto.getFiles() != null) {
            for (File file : dto.getFiles()) {
                mmh.addAttachment(file.getName(), file);
            }
        }
        return mimeMessage;
    }

    /**
     * 邮件消息提供者
     */
    public interface MessageProvider {

        /**
         * 获取消息
         * @param session Session
         * @throws UnsupportedEncodingException 编码不支持
         * @throws MessagingException 消息异常
         * @return 消息
         */
        MimeMessage getMessage(Session session) throws UnsupportedEncodingException, MessagingException;
    }
}
