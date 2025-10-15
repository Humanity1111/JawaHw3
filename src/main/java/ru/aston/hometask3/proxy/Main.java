package ru.aston.hometask3.proxy;

import java.util.logging.Logger;

interface Payment {
    void pay(double amount);
}

class CashPayment implements Payment {
    private static final Logger logger = Logger.getLogger(CashPayment.class.getName());

    @Override
    public void pay(double amount) {
        logger.info("Paid with cash: $" + amount);
    }
}

class CardPaymentProxy implements Payment {
    private static final Logger logger = Logger.getLogger(CardPaymentProxy.class.getName());
    private final CashPayment cashPayment;
    private final String cardNumber;

    public CardPaymentProxy(String cardNumber) {
        this.cashPayment = new CashPayment();
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        if (validateCard()) {
            logger.info("Payment approved for card: " + maskCardNumber());
            cashPayment.pay(amount);
        } else {
            logger.warning("Card validation failed: " + cardNumber);
        }
    }

    private boolean validateCard() {
        return cardNumber != null && cardNumber.length() == 16;
    }

    private String maskCardNumber() {
        return "**** **** **** " + cardNumber.substring(12);
    }
}

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Payment card = new CardPaymentProxy("1234567812345678");
        card.pay(100.0);

        Payment invalidCard = new CardPaymentProxy("1234");
        invalidCard.pay(50.0);
    }
}
