package pl.marceen.nurseryqueueapi.email.entity;

/**
 * @author Marcin Zaremba
 */
public class EmailData {
    private String emailsFrom;
    private String emailsTo;
    private String subject;
    private String text;

    public String getEmailsFrom() {
        return emailsFrom;
    }

    public EmailData emailsFrom(String emailsFrom) {
        this.emailsFrom = emailsFrom;
        return this;
    }

    public String getEmailsTo() {
        return emailsTo;
    }

    public EmailData emailsTo(String emailsTo) {
        this.emailsTo = emailsTo;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public EmailData subject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getText() {
        return text;
    }

    public EmailData text(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmailData{");
        sb.append("emailsFrom='").append(emailsFrom).append('\'');
        sb.append(", emailsTo='").append(emailsTo).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
