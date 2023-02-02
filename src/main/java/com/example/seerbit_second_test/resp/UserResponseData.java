package com.example.seerbit_second_test.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseData {

    private ResponseData respData;
    private JwtResponseData tokenData;
}
