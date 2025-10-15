package main.java.ru.aston.hometask3.chain;

import java.util.logging.Logger;

abstract class Handler {
    protected Handler next;
    protected final Logger logger = Logger.getLogger(this.getClass().getName());

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handleRequest(String request);
}

class AutoResponder extends Handler {
    @Override
    public void handleRequest(String request) {
        logger.info("AutoResponder: trying to help with \"" + request + "\"");
        if (request.contains("standard")) {
            logger.info("AutoResponder: problem solved automatically!");
        } else {
            logger.info("AutoResponder: cannot help, transferring to operator...");
            if (next != null) {
                next.handleRequest(request);
            }
        }
    }
}

class Operator extends Handler {
    @Override
    public void handleRequest(String request) {
        logger.info("Operator: received the request \"" + request + "\"");
        if (request.contains("simple")) {
            logger.info("Operator: please try turning the computer off and on again.");
        } else {
            logger.info("Operator: transferring the call to engineer...");
            if (next != null) {
                next.handleRequest(request);
            }
        }
    }
}

class Engineer extends Handler {
    @Override
    public void handleRequest(String request) {
        logger.info("Engineer: analyzing the request \"" + request + "\"");
        logger.info("Engineer: found a solution! Download proper drivers and install them for Ubuntu.");
    }
}

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Handler autoResponder = new AutoResponder();
        Handler operator = new Operator();
        Handler engineer = new Engineer();

        autoResponder.setNext(operator);
        operator.setNext(engineer);

        logger.info("Starting tech support call...");

        autoResponder.handleRequest("New video card not working on Ubuntu");

        logger.info("Support call finished.");
    }
}
