package com.catalog.model;

import java.util.Map;

public class PolynomialRequest {
	private Map<String, Integer> keys; // n and k
    private Map<String, Map<String, String>> roots; // base and value pairs

    public Map<String, Integer> getKeys() {
        return keys;
    }

    public void setKeys(Map<String, Integer> keys) {
        this.keys = keys;
    }

    public Map<String, Map<String, String>> getRoots() {
        return roots;
    }

    public void setRoots(Map<String, Map<String, String>> roots) {
        this.roots = roots;
    }

}
