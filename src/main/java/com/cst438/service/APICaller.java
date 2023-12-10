package com.cst438.service;

import org.springframework.beans.factory.annotation.Value;

public class APICaller {

    @Value("${apiKey}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
