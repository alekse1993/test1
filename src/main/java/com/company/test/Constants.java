package com.company.test;

import org.json.JSONObject;


public class Constants {
    final static String data = "{\n" +
            "  \"success\": true,\n" +
            "  \"messages\": [\n" +
            "    {\n" +
            "      \"code\": \"FxMarketUnavailable\",\n" +
            "      \"text\": \"Данные по валютному рынку недоступны\",\n" +
            "      \"title\": \"S02WDK7\",\n" +
            "      \"type\": \"warning\",\n" +
            "      \"uuid\": \"84d72ea1-3943-4dc6-8e43-47273224484f\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"body\": {\n" +
            "    \"markets\": [],\n" +
            "    \"mCalls\": [],\n" +
            "    \"agreementId\": \"S02WDK7\",\n" +
            "    \"position\": [\n" +
            "      {\n" +
            "        \"date\": \"2021-12-22\",\n" +
            "        \"total\": {\n" +
            "          \"value\": \"303712.87\",\n" +
            "          \"currencyCode\": \"\\u20bd\"\n" +
            "        },\n" +
            "        \"markets\": [\n" +
            "          {\n" +
            "            \"market\": \"fond\",\n" +
            "            \"total\": {\n" +
            "              \"value\": \"303712.87\",\n" +
            "              \"currencyCode\": \"\\u20bd\"\n" +
            "            }\n" +
            "          }\n" +
            "        ],\n" +
            "        \"assets\": [\n" +
            "          {\n" +
            "            \"market\": \"fond\",\n" +
            "            \"instrumentType\": \"fxbase\",\n" +
            "            \"secCode\": \"RUB\",\n" +
            "            \"currentCost\": {\n" +
            "              \"value\": \"1727.51\",\n" +
            "              \"currencyCode\": \"\\u20bd\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"market\": \"all\",\n" +
            "            \"instrumentType\": \"fxbase\",\n" +
            "            \"secCode\": \"RUB\",\n" +
            "            \"currentCost\": {\n" +
            "              \"value\": \"1727.51\",\n" +
            "              \"currencyCode\": \"\\u20bd\"\n" +
            "            }\n" +
            "          }\n" +
            "\n" +
            "        ]\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    final static JSONObject jsonObject = new JSONObject(data);
}
