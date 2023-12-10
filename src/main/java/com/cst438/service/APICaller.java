package com.cst438.service;

import org.springframework.beans.factory.annotation.Value;

public class APICaller {

    @Value("${apiKey}")
    private String apiKey;


}
