package com.garrett.domain.protocol;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SmtpContext {

    private StringBuilder protoDataBuilder = new StringBuilder();
    private String helo;
    private String ehlo;
    private String mail;
    private String rcpt;
    private String data;
    private String content;
    private String quit;

    private String protoData;
    private String toString;
    private String fromEmail;
    private String toEmail;

    public SmtpContext() {
    }

    public void appendProtoMsg(String msg) {
        protoDataBuilder.append(msg).append("\n");
    }

    public void build() {
        this.protoData = protoDataBuilder.toString();
        this.toString = "HELO;" + helo + '\n' +
                        "EHLO;" + mail + '\n' +
                        "MAIL;" + mail + '\n' +
                        "RCPT;" + rcpt + '\n' +
                        "DATA;" + data + '\n' +
                        "CONTENT;" + content + '\n' +
                        "QUIT;" + quit;

    }

    @Override
    public String toString() {
        if (toString != null) {
            return toString;
        }
        return  "HELO;" + helo + '\n' +
                "EHLO;" + mail + '\n' +
                "MAIL;" + mail + '\n' +
                "RCPT;" + rcpt + '\n' +
                "DATA;" + data + '\n' +
                "CONTENT;" + content + '\n' +
                "QUIT;" + quit;
    }
}
