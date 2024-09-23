package com.catalog.service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class PolynomialService {

    // Function to convert a string from any base to decimal (base 10)
    public long convertToDecimal(String value, int base) {
        long result = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            int digit = (c >= '0' && c <= '9') ? c - '0' : c - 'A' + 10;
            result = result * base + digit;
        }
        return result;
    }

    // Lagrange interpolation function to find the constant term 'c'
    public double lagrangeInterpolation(List<Map.Entry<Integer, Double>> points) {
        double result = 0.0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            double term = points.get(i).getValue();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    term *= (0.0 - points.get(j).getKey()) / (points.get(i).getKey() - points.get(j).getKey());
                }
            }
            result += term;
        }

        return result;
    }

    // Method to process the input JSON and compute the result
    public double processPolynomial(JsonNode inputJson) {
        // Read n and k from the "keys" field
        int n = inputJson.get("keys").get("n").asInt();
        int k = inputJson.get("keys").get("k").asInt();

        List<Map.Entry<Integer, Double>> points = new ArrayList<>();

        // Iterate through the keys representing the roots
        Iterator<String> fieldNames = inputJson.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            if (!fieldName.equals("keys")) {
                int x = Integer.parseInt(fieldName);
                JsonNode root = inputJson.get(fieldName);
                int base = root.get("base").asInt();
                String value = root.get("value").asText();

                // Decode the y value based on the base
                double y = convertToDecimal(value, base);
                points.add(new AbstractMap.SimpleEntry<>(x, y));
            }
        }

        // Now calculate the constant term 'c' using Lagrange interpolation
        return lagrangeInterpolation(points);
    }

}
