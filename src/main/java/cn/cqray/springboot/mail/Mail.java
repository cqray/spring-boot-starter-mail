package cn.cqray.springboot.mail;

/**
 * 邮件相关配置
 * @author Cqray
 */
public class Mail {

    private String host;
    private Integer port;
    private String username;
    private String nickname;
    private String password;
    private String encoding;
    private Boolean auth;
    private Boolean debug;
    private Boolean ssl;
    private Long timeout;
    private MailProtocol protocol = MailProtocol.SMTP;

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getEncoding() {
        return encoding;
    }

    public boolean isAuth() {
        return auth == null ? false : auth;
    }

    public boolean isDebug() {
        return debug == null ? false : debug;
    }

    public boolean isSsl() {
        return ssl == null ? false : ssl;
    }

    public MailProtocol getProtocol() {
        return protocol;
    }

    public Long getTimeout() {
        return timeout;
    }

    public Mail host(String host) {
        this.host = host;
        return this;
    }

    public Mail port(int port) {
        this.port = port;
        return this;
    }

    public Mail username(String username) {
        this.username = username;
        return this;
    }

    public Mail password(String password) {
        this.password = password;
        return this;
    }

    public Mail nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Mail encoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public Mail auth(boolean auth) {
        this.auth = auth;
        return this;
    }

    public Mail debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public Mail ssl(boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    public Mail timeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    public Mail protocal(MailProtocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public Mail mix(MailProtocolBase protocol) {
        if (isEmpty(host)) {
            host = protocol.getHost();
        }
        if (port == null) {
            port = protocol.getPort();
        }
        if (isEmpty(username)) {
            username = protocol.getUsername();
        }
        if (isEmpty(password)) {
            password = protocol.getPassword();
        }
        if (isEmpty(encoding)) {
            encoding = protocol.getEncoding();
        }
        if (auth == null) {
            auth = protocol.isAuth();
        }
        if (debug == null) {
            debug = protocol.isDebug();
        }
        if (timeout == null) {
            timeout = protocol.getTimeout();
        }
        ssl = !protocol.getSocketFactory().isEmpty();
        return this;
    }

    static boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }

}
