package com.example.seerbit_second_test.service;

import com.example.seerbit_second_test.dto.LoginDto;
import com.example.seerbit_second_test.dto.UserDto;
import com.example.seerbit_second_test.resp.UserResponseData;

public interface UserService {
    UserResponseData signup(UserDto userDto);
    UserResponseData login(LoginDto loginDto) throws Exception;
}
