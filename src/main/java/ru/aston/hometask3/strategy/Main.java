package ru.aston.hometask3.chain;

import java.util.logging.Logger;

interface RouteStrategy {
    void buildRoute(String start, String end);
}

class CarRoute implements RouteStrategy {
    private static final Logger logger = Logger.getLogger(CarRoute.class.getName());

    @Override
    public void buildRoute(String start, String end) {
        logger.info("Building route for car from " + start + " to " + end);
    }
}

class WalkingRoute implements RouteStrategy {
    private static final Logger logger = Logger.getLogger(WalkingRoute.class.getName());

    @Override
    public void buildRoute(String start, String end) {
        logger.info("Building walking route from " + start + " to " + end);
    }
}

class BicycleRoute implements RouteStrategy {
    private static final Logger logger = Logger.getLogger(BicycleRoute.class.getName());

    @Override
    public void buildRoute(String start, String end) {
        logger.info("Building bicycle route from " + start + " to " + end);
    }
}

class Navigator {
    private static final Logger logger = Logger.getLogger(Navigator.class.getName());
    private RouteStrategy strategy;

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
        logger.info("Navigation strategy set: " + strategy.getClass().getSimpleName());
    }

    public void navigate(String start, String end) {
        if (strategy == null) {
            logger.warning("No strategy selected!");
            return;
        }
        strategy.buildRoute(start, end);
    }
}

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Navigator navigator = new Navigator();

        navigator.setStrategy(new CarRoute());
        navigator.navigate("Home", "Office");

        navigator.setStrategy(new WalkingRoute());
        navigator.navigate("Park", "Cafe");

        navigator.setStrategy(new BicycleRoute());
        navigator.navigate("House", "Gym");

        logger.info("All routes built successfully!");
    }
}
