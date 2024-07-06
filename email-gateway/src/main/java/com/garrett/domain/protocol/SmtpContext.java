package com.garrett.domain.protocol;


public record SmtpContext(String helo, String mail, String rcpt, String data, String content, String quit) {

    public static SmtpProtocolBuilder builder() {
        return new SmtpProtocolBuilder();
    }

    public static class SmtpProtocolBuilder {
        private String helo;
        private String mail;
        private String rcpt;
        private String data;
        private String content;
        private String quit;

        public SmtpProtocolBuilder helo(String helo) {
            this.helo = helo;
            return this;
        }

        public SmtpProtocolBuilder mail(String mail) {
            this.mail = mail;
            return this;
        }

        public SmtpProtocolBuilder rcpt(String rcpt) {
            this.rcpt = rcpt;
            return this;
        }

        public SmtpProtocolBuilder data(String data) {
            this.data = data;
            return this;
        }

        public SmtpProtocolBuilder content(String content) {
            this.content = content;
            return this;
        }

        public SmtpProtocolBuilder quit(String quit) {
            this.quit = quit;
            return this;
        }

        public SmtpContext build() {
            return new SmtpContext(helo, mail, rcpt, data, content, quit);
        }
    }
}
