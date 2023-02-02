package com.example.seerbit_second_test.service.implementation;

import com.example.seerbit_second_test.auth.JwtTokenManager;
import com.example.seerbit_second_test.auth.SeerbitUserDetailsService;
import com.example.seerbit_second_test.dto.LoginDto;
import com.example.seerbit_second_test.dto.UserDto;
import com.example.seerbit_second_test.model.User;
import com.example.seerbit_second_test.repository.UserRepository;
import com.example.seerbit_second_test.resp.JwtResponseData;
import com.example.seerbit_second_test.resp.ResponseData;
import com.example.seerbit_second_test.resp.UserResponseData;
import com.example.seerbit_second_test.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper; //needs to create the bean
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder; //needs to create the bean

    @Autowired
    private AuthenticationManager authenticationManager; // needs to create bean
    @Autowired
    private SeerbitUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenManager tokenManager;

    @Override
    public UserResponseData signup(UserDto userDto) {
        UserResponseData userRespData = new UserResponseData();
        ResponseData respData = new ResponseData();

        var isEmailExist = userRepo.findByEmail(userDto.getEmail());
        if(null != isEmailExist){
            respData.setCode("204");
            respData.setMessage("Email has already been registered by another customer");
            userRespData.setRespData(respData);
        }
        var user = convertUserDtoToEntity(userDto);
        user.setHashPassword(passwordEncoder.encode(userDto.getPassword()));
        saveUser(user);
        respData.setCode("201");
        respData.setMessage("Welcome to seerbit payment service");
        userRespData.setRespData(respData);
        return userRespData;
    }

    @Override
    public UserResponseData login(LoginDto loginDto) throws Exception{
        UserResponseData userRespData = new UserResponseData();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        JwtResponseData jwtRespData = new JwtResponseData(jwtToken);
        userRespData.setTokenData(jwtRespData);
        return userRespData;
    }

    private void saveUser(User user){
        userRepo.save(user);
    }

    private User convertUserDtoToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }
}
