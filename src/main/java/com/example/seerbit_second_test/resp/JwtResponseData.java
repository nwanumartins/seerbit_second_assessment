package com.example.seerbit_second_test.resp;

import java.io.Serializable;

public class JwtResponseData implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String token;

    public JwtResponseData(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
