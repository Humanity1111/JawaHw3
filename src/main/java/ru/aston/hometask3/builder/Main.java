package ru.aston.hometask3.builder;

import java.util.logging.Logger;

// Класс дома, который мы строим
class House {
    private String foundation;
    private String walls;
    private String roof;
    private boolean garage;
    private boolean garden;

    @Override
    public String toString() {
        return "House {" +
                "foundation='" + foundation + '\'' +
                ", walls='" + walls + '\'' +
                ", roof='" + roof + '\'' +
                ", garage=" + garage +
                ", garden=" + garden +
                '}';
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }
}

abstract class HouseBuilder {
    protected House house;
    protected final Logger logger = Logger.getLogger(this.getClass().getName());

    public void createNewHouse() {
        house = new House();
    }

    public House getHouse() {
        return house;
    }

    public abstract void buildFoundation();

    public abstract void buildWalls();

    public abstract void buildRoof();

    public abstract void buildGarage();

    public abstract void buildGarden();
}

class SimpleHouseBuilder extends HouseBuilder {
    @Override
    public void buildFoundation() {
        logger.info("Laying simple concrete foundation");
        house.setFoundation("Concrete foundation");
    }

    @Override
    public void buildWalls() {
        logger.info("Building brick walls");
        house.setWalls("Brick walls");
    }

    @Override
    public void buildRoof() {
        logger.info("Adding tile roof");
        house.setRoof("Tile roof");
    }

    @Override
    public void buildGarage() {
        logger.info("No garage for this simple house");
        house.setGarage(false);
    }

    @Override
    public void buildGarden() {
        logger.info("Planting a small garden");
        house.setGarden(true);
    }
}

class LuxuryHouseBuilder extends HouseBuilder {
    @Override
    public void buildFoundation() {
        logger.info("Laying deep reinforced foundation");
        house.setFoundation("Reinforced concrete foundation");
    }

    @Override
    public void buildWalls() {
        logger.info("Building marble walls");
        house.setWalls("Marble walls");
    }

    @Override
    public void buildRoof() {
        logger.info("Adding glass roof");
        house.setRoof("Glass roof");
    }

    @Override
    public void buildGarage() {
        logger.info("Adding big garage");
        house.setGarage(true);
    }

    @Override
    public void buildGarden() {
        logger.info("Designing luxury garden with fountain");
        house.setGarden(true);
    }
}

class Director {
    private HouseBuilder builder;

    public void setBuilder(HouseBuilder builder) {
        this.builder = builder;
    }

    public House constructHouse() {
        builder.createNewHouse();
        builder.buildFoundation();
        builder.buildWalls();
        builder.buildRoof();
        builder.buildGarage();
        builder.buildGarden();
        return builder.getHouse();
    }
}

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Director director = new Director();

        HouseBuilder simpleBuilder = new SimpleHouseBuilder();
        director.setBuilder(simpleBuilder);
        House simpleHouse = director.constructHouse();
        logger.info("Built a simple house: " + simpleHouse);

        HouseBuilder luxuryBuilder = new LuxuryHouseBuilder();
        director.setBuilder(luxuryBuilder);
        House luxuryHouse = director.constructHouse();
        logger.info("Built a luxury house: " + luxuryHouse);
    }
}
