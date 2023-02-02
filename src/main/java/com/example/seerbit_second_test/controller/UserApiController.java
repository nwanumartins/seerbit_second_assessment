package com.example.seerbit_second_test.controller;

import com.example.seerbit_second_test.dto.LoginDto;
import com.example.seerbit_second_test.dto.UserDto;
import com.example.seerbit_second_test.resp.UserResponseData;
import com.example.seerbit_second_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/seerbit")
public class UserApiController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseData> signup(@RequestBody UserDto userDto){
        var resp = userService.signup(userDto);
        if(!resp.getRespData().getCode().equalsIgnoreCase("201"))
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseData> login(@RequestBody LoginDto loginDto) throws Exception {
        var loginResp = userService.login(loginDto);
        return new ResponseEntity<UserResponseData>(loginResp,HttpStatus.OK);
    }
}
