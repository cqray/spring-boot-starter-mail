package cn.cqray.springboot.mail;

import java.io.File;

/**
 * 需要传递的Mail数据
 * @author Cqray
 */
public class MailDto {

    /** 标题 **/
    private String subject;
    /** 内容 **/
    private String content;
    /** 是否是Html内容 **/
    private boolean html;
    /** 收件人 **/
    private String to;
    /** 抄送人 **/
    private String[] cc;
    /** 密送人 **/
    private String[] bcc;
    /** 附件 **/
    private File[] files;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }
}
