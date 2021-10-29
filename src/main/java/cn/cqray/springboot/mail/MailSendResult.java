package cn.cqray.springboot.mail;

/**
 * 邮件发送结果
 * @author Cqray
 */
public class MailSendResult {

    /** 是否成功 **/
    private boolean success;
    /** 异常信息 **/
    private String exception;

    MailSendResult() {
        this.success = true;
    }

    MailSendResult(String exception) {
        this.exception = exception;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getException() {
        return exception;
    }
}
