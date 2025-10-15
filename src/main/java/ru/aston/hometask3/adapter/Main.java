package ru.aston.hometask3.adapter;

import java.util.logging.Logger;

interface StockDataProvider {
    String getData();
}

class XMLStockDataProvider implements StockDataProvider {
    @Override
    public String getData() {
        return "<stocks><stock symbol='AAPL' price='175'/></stocks>";
    }
}

class JSONAnalyticsLibrary {
    private static final Logger logger = Logger.getLogger(JSONAnalyticsLibrary.class.getName());

    public void processJSON(String jsonData) {
        logger.info("Processing JSON data: " + jsonData);
    }
}

class StockDataAdapter implements StockDataProvider {
    private final JSONAnalyticsLibrary jsonLibrary;
    private final StockDataProvider xmlProvider;

    public StockDataAdapter(StockDataProvider xmlProvider, JSONAnalyticsLibrary jsonLibrary) {
        this.xmlProvider = xmlProvider;
        this.jsonLibrary = jsonLibrary;
    }

    @Override
    public String getData() {
        String xml = xmlProvider.getData();
        String json = convertXmlToJson(xml);
        jsonLibrary.processJSON(json);
        return json;
    }

    private String convertXmlToJson(String xml) {
        return "{ \"stocks\": [{ \"symbol\": \"AAPL\", \"price\": 175 }] }";
    }
}

public class Main {
    public static void main(String[] args) {
        StockDataProvider xmlProvider = new XMLStockDataProvider();
        JSONAnalyticsLibrary jsonLibrary = new JSONAnalyticsLibrary();
        StockDataProvider adapter = new StockDataAdapter(xmlProvider, jsonLibrary);

        adapter.getData();
    }
}
