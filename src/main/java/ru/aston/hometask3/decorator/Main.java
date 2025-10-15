package ru.aston.hometask3.decorator;

import java.util.logging.Logger;

interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    private static final Logger logger = Logger.getLogger(EmailNotifier.class.getName());
    private final String[] admins;

    public EmailNotifier(String... admins) {
        this.admins = admins;
    }

    @Override
    public void send(String message) {
        for (String admin : admins) {
            logger.info("Email sent to " + admin + ": " + message);
        }
    }
}

abstract class NotifierDecorator implements Notifier {
    protected final Notifier wrappee;

    public NotifierDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }
}

class SMSDecorator extends NotifierDecorator {
    private static final Logger logger = Logger.getLogger(SMSDecorator.class.getName());

    public SMSDecorator(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send(String message) {
        wrappee.send(message);
        sendSMS(message);
    }

    private void sendSMS(String message) {
        logger.info("SMS sent: " + message);
    }
}

class PushDecorator extends NotifierDecorator {
    private static final Logger logger = Logger.getLogger(PushDecorator.class.getName());

    public PushDecorator(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send(String message) {
        wrappee.send(message);
        sendPush(message);
    }

    private void sendPush(String message) {
        logger.info("Push notification sent: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier("admin1@example.com", "admin2@example.com");
        Notifier smsNotifier = new SMSDecorator(emailNotifier);
        Notifier pushNotifier = new PushDecorator(smsNotifier);

        pushNotifier.send("Server is down!");
    }
}
